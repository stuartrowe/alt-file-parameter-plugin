/*
 * The MIT License
 *
 * Copyright 2020 CloudBees, Inc.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package io.jenkins.plugins.alt_file_parameter;

import hudson.EnvVars;
import hudson.model.Run;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.io.IOUtils;
import org.kohsuke.stapler.DataBoundConstructor;

public final class Base64FileParameterValue extends AbstractFileParameterValue {
    
    private final String base64;

    @DataBoundConstructor public Base64FileParameterValue(String name, FileItem file) throws IOException {
        this(name, file.getInputStream());
        file.delete();
    }

    Base64FileParameterValue(String name, InputStream src) throws IOException {
        super(name);
        base64 = Base64.getEncoder().encodeToString(IOUtils.toByteArray(src));
    }

    @Override public void buildEnvironment(Run<?, ?> build, EnvVars env) {
        env.put(name, base64);
    }

    // TODO createVariableResolver if desired for freestyle

    @Override public Object getValue() {
        return base64;
    }

    @Override protected InputStream open() throws IOException {
        return new ByteArrayInputStream(Base64.getDecoder().decode(base64));
    }

}
