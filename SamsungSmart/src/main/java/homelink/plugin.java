package homelink;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

import me.BL19.HomeLink.HAP.Devices.HAPDevice;
import me.BL19.HomeLink.HAP.Devices.HAPDeviceType;
import me.BL19.HomeLink.HAP.Devices.Light;
import me.BL19.HomeLink.Plugin.HLPlugin;
import samsungapi.Client;
import samsungapi.Command;

public class plugin extends HLPlugin {

	Client c;
	
	@Override
	public void onEnable() {
		homeLink.addDeviceToHAP(new Light("TV", homeLink.getNextID()), HAPDeviceType.LIGHT, false);
		Properties p = getDefaultConfig();
		
		if(!new File("plugins/config/samsungsmart.xml").exists()) {
			try {
				new File("plugins/config/samsungsmart.xml").createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			System.out.println("Creating new file config for SMSTV");
			System.err.println("== PLEASE CONFIGURE THE SAMSUNG SMART TV PLUGIN BEFORE USING IT ==");
		}
		
		
		try {
			p.loadFromXML(new FileInputStream("plugins/config/samsungsmart.xml"));
		} catch (InvalidPropertiesFormatException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		c = new Client(p.getProperty("IP"), p.getProperty("MAC"), p.getProperty("NAME"));
		System.out.println("SMSTV client connected!");
		try {
			p.storeToXML(new FileOutputStream("plugins/config/samsungsmart.xml"), "Samsung Smart TV Plugin");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Properties getDefaultConfig() {
		Properties p = new Properties();
		p.setProperty("IP", "192.168.0.180");
		p.setProperty("MAC", "01-23-45-67-89-ad");
		p.setProperty("NAME", "HomeLink");
		return p;
	}
	
	@Override
	public void deviceStateChange(HAPDevice d) {
		if(d.name == "TV") {
			Light l = (Light) d.device;
			if(!l.getLightbulbPowerState().join()) {
				// Turn off tv
				
				c.sendCommand(Command.KEY_POWER_OFF);
			} else {
				// Turn on tv
				c.sendCommand(Command.KEY_POWER_OFF);
			}
		}
	}
	
	
	
}
