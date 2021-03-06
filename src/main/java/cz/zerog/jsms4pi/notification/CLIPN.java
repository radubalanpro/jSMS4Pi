package cz.zerog.jsms4pi.notification;

/*
 * #%L
 * jSMS4Pi
 * %%
 * Copyright (C) 2015 jSMS4Pi
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-3.0.html>.
 * #L%
 */

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author zerog
 */
public final class CLIPN implements Notification {

    //+CLIP: "+420739474009",14
    private final static Pattern pattern = Pattern.compile("\\+CLIP: *\"(\\+?\\d+)\",(\\d+)");
    
    private final String callerId;
    private final String response;

    private CLIPN(Matcher matcher, String response) {
        callerId = matcher.group(1);
        this.response = response;
    }

    @Override
    public String getResponse() {
        return response;
    }

    public static CLIPN tryParse(String notification) {
        Matcher matcher = pattern.matcher(notification);
        if (matcher.matches()) {
            return new CLIPN(matcher,notification);
        } 
        return null;
    }

    public String getCallerId() {
        return callerId;
    }
}
