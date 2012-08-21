
package com.openmhealth.ohmage.core;

import android.os.RemoteException;
import android.util.Log;

import gov.va.ptsd.ptsdcoach.PTSDApplication;

import org.json.JSONException;
import org.json.JSONObject;

public class EventLog {

    public static String CAMPAIGN_URN = "urn:campaign:va:ptsd_explorer";
    public static String CAMPAIGN_CREATION = "2011-12-18 15:21:04";

    private static final String OBSERVER_ID = "com.openmhealth.ohmage.va.ptsd_explorer";
    private static final int OBSERVER_VERSION = 20120820;

    static public void log(EventRecord event) {
        try {
            JSONObject obj = event.toJSON();
            PTSDApplication.getProbeWriter().writeResponse(CAMPAIGN_URN, CAMPAIGN_CREATION,
                    obj.toString());
            // Do something useful...
            Log.v("ptsdexplorer", obj.toString(4));
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    static public void logProbe(ProbeRecord event) {
        try {
            event.buildProbe(OBSERVER_ID, OBSERVER_VERSION).write(PTSDApplication.getProbeWriter());
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
