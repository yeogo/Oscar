package com.comcast.oscar.cli.commands;

import java.io.File;
import java.io.FileNotFoundException;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;

import com.comcast.oscar.configurationfile.ConfigurationFileException;
import com.comcast.oscar.configurationfile.ConfigurationFileExport;
import com.comcast.oscar.configurationfile.ConfigurationFileImport;
import com.comcast.oscar.configurationfile.DigitMapOperation;

/**
 * 
 * @author Allen Flickinger (allen.flickinger@gmail.com)
 * 
 * @bannerLicense
	Copyright 2015 Comcast Cable Communications Management, LLC<br>
	___________________________________________________________________<br>
	Licensed under the Apache License, Version 2.0 (the "License")<br>
	you may not use this file except in compliance with the License.<br>
	You may obtain a copy of the License at<br>
	http://www.apache.org/licenses/LICENSE-2.0<br>
	Unless required by applicable law or agreed to in writing, software<br>
	distributed under the License is distributed on an "AS IS" BASIS,<br>
	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.<br>
	See the License for the specific language governing permissions and<br>
	limitations under the License.<br>

 */

public class DigitmapDisplay {
	
	/**
	 * Set option parameters for command Digitmap display
	 * @return Option
	 */
	public static final Option OptionParameters() {
		OptionBuilder.withValueSeparator(' ');
		OptionBuilder.withLongOpt("displaydigitmap");
    	OptionBuilder.withDescription("Display the DigitMap of the input file - PacketCable ONLY.");
    	return OptionBuilder.create("ddm");
	}
	
	/**
	 * Prints digitmap if file is binary
	 * @param file
	 */
	public void printDigitmapDisplayFromBinary(File file) {
		ConfigurationFileExport cfePacketCable = new ConfigurationFileExport(file, ConfigurationFileExport.DOCSIS_PKTCBL);	
		DigitMapOperation dmo = new DigitMapOperation(cfePacketCable);
		System.out.println(dmo.getDigitMap());
	}

	/**
	 * Prints digitmap if file is text
	 * @param file
	 */
	public void printDigitmapDisplayFromText(File file) {
		ConfigurationFileImport cfi = null;
		try {
			cfi = new ConfigurationFileImport(file);
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		catch (ConfigurationFileException e) {
			e.printStackTrace();
		}
		
		if (cfi != null) {
			DigitMapOperation dmo = new DigitMapOperation(cfi);
			System.out.println(dmo.getDigitMap());
		}
	}
}
