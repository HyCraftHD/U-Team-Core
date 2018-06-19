/*
 * Copyright 2017 John Grosh (john.a.grosh@gmail.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 *
 * Modifications Copyright (C) 2018 https://U-Team.info
 */
package info.u_team.u_team_core.repack.com.jagrosh.discordipc.exceptions;

import info.u_team.u_team_core.repack.com.jagrosh.discordipc.entities.DiscordBuild;

/**
 * An exception thrown when an
 * {@link info.u_team.u_team_core.repack.com.jagrosh.discordipc.IPCClient
 * IPCClient} when the client cannot find the proper application to use for
 * RichPresence when attempting to
 * {@link info.u_team.u_team_core.repack.com.jagrosh.discordipc.IPCClient#connect(DiscordBuild...)
 * connect}.
 * <p>
 *
 * This purely and always means the IPCClient in question (specifically the
 * client ID) is <i>invalid</i> and features using this library cannot be
 * accessed using the instance.
 *
 * @author John Grosh (john.a.grosh@gmail.com)
 */
public class NoDiscordClientException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
}
