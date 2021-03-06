/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.pleuvoir.core;

import java.util.regex.Pattern;

/**
 * Constants
 */
public class Constants {

	public static final String ANYHOST_KEY = "anyhost";

	public static final String ANYHOST_VALUE = "0.0.0.0";

	public static final String LOCALHOST_KEY = "localhost";

	public static final String LOCALHOST_VALUE = "127.0.0.1";
	
	public static final String BACKUP_KEY = "backup";
	
	public static final Pattern COMMA_SPLIT_PATTERN = Pattern.compile("\\s*[,]+\\s*");

	public static final String INTERFACE_KEY = "interface";

	public static final String VERSION_KEY = "version";

	public static final String GROUP_KEY = "group";

	public static final String DEFAULT_KEY_PREFIX = "default.";
}
