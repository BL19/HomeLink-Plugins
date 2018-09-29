package homelink;

import com.beowulfe.hap.accessories.DimmableLightbulb;

import me.BL19.HomeLink.HAP.Devices.Dimmer;
import me.BL19.HomeLink.HAP.Devices.HAPDevice;
import me.BL19.HomeLink.HAP.Devices.HAPDeviceType;
import me.BL19.HomeLink.HAP.Devices.Light;
import me.BL19.HomeLink.Plugin.HLPlugin;
import me.BL19.HomeLink.Plugin.TriggerElement;

public class plugin extends HLPlugin {

	public plugin() {
		System.out.println("NEW INSTANCE!");
	}

	@Override
	public void onEnable() {
		System.out.println("DEVPlugin has successfully been enabled!");
		homeLink.addDeviceToHAP(new Light("DEVPLUGIN", homeLink.getNextID()), HAPDeviceType.LIGHT, true);
	}

	@Override
	public void onDisable() {
		System.out.println("DEVPlugin is being disabled!");
		super.onDisable();
	}

	@Override
	public void trigger(TriggerElement te, String data) {
		System.out.println("TRIGGER: (" + te + ": " + data + ")");
	}

	@Override
	public void deviceStateChange(HAPDevice d) {
		System.out.println("Device changed state");
		System.out.println("\tName: " + d.name + "\n\tid: " + d.id);
		switch (d.deviceType) {
		case DIMMABLE:
			Dimmer dim = (Dimmer) d.device;
			System.out.println("\t\tState: " + (dim.getLightbulbPowerState().join()) + "\n\t\tValue: " + dim.getBrightness().join());
			break;
		case LIGHT:
			Light l = (Light) d.device;
			System.out.println("\tState: " + (l.getLightbulbPowerState().join()));
			break;
		case SWITCH:
			break;
		default:
			break;
		}
	}

}
