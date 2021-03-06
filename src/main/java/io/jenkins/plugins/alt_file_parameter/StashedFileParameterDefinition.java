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

import hudson.Extension;
import hudson.model.ParameterDefinition;
import java.io.IOException;
import java.io.InputStream;
import org.kohsuke.stapler.DataBoundConstructor;

public final class StashedFileParameterDefinition extends AbstractFileParameterDefinition {

    @DataBoundConstructor public StashedFileParameterDefinition(String name, String description) {
        super(name, description);
    }

    @Override protected Class<? extends AbstractFileParameterValue> valueType() {
        return StashedFileParameterValue.class;
    }

    @Override protected AbstractFileParameterValue createValue(String name, InputStream src) throws IOException {
        return new StashedFileParameterValue(name, src);
    }

    @Extension public static final class DescriptorImpl extends ParameterDefinition.ParameterDescriptor {

        @Override public String getDisplayName() {
            return "Stashed File Parameter";
        }

    }

}
