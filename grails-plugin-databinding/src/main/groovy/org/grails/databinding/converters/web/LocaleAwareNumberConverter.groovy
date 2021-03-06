/*
 * Copyright 2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.grails.databinding.converters.web

import groovy.transform.CompileStatic

import java.text.NumberFormat

import org.codehaus.groovy.grails.web.servlet.mvc.GrailsWebRequest
import org.grails.databinding.converters.ValueConverter

/**
 * A ValueConverter that knows how to convert a String to any numeric type and is Locale aware.  The
 * converter will use the Locale of the current request if being invoked as part of a
 * request, otherwise will use Locale.getDefault()
 *
 * @author Jeff Brown
 * @since 2.3
 */
@CompileStatic
class LocaleAwareNumberConverter implements ValueConverter {

    Class<?> targetType

    boolean canConvert(value) {
        value instanceof String
    }

    Object convert(value) {
        numberFormatter.parse((String)value).asType(getTargetType())
    }

    protected NumberFormat getNumberFormatter() {
        def request = GrailsWebRequest.lookup()
        def locale = request ? request.getLocale() : Locale.getDefault()
        NumberFormat.getInstance(locale)
    }
}
