package com.homelink.pubsub;

import com.pubnub.api.Callback;
import com.pubnub.api.Pubnub;
import com.pubnub.api.PubnubError;
import com.pubnub.api.PubnubException;

import org.json.JSONObject;

/**
 * Created by abhijit on 7/6/15.
 */
public class PubnubImpl extends PubSub {

    private static Pubnub mPubnub;
    private static Callback mPubnubCallback;

    public static PubSub init() {
        if(mPubSubInstance == null) mPubSubInstance = createInstance();
        return mPubSubInstance;
    }

    private static PubSub createInstance() {
        if(mPubSubInstance == null) mPubSubInstance = new PubnubImpl();
        return mPubSubInstance;
    }

    private PubnubImpl() {
        super();
        mPubnub = new Pubnub("pub-c-0d1e3317-dac1-4508-8def-f750890c1259", "sub-c-37743c0c-1add-11e5-bbfe-0619f8945a4f");
        mPubnubCallback = new Callback() {
            @Override
            public void successCallback(String channel, Object message, String timetoken) {
                super.successCallback(channel, message, timetoken);
                System.out.println("SUBSCRIBE : SUCCESS on channel:" + channel
                        + " : " + message.getClass() + " : "
                        + message.toString());
                if(mListener != null) {
                    mListener.onMessage(channel, (JSONObject) message);
                }
            }

            @Override
            public void errorCallback(String channel, PubnubError error) {
                super.errorCallback(channel, error);
                System.out.println("SUBSCRIBE : ERROR on channel " + channel
                        + " : " + error.toString());
            }

            @Override
            public void connectCallback(String channel, Object message) {
                super.connectCallback(channel, message);
                System.out.println("SUBSCRIBE : CONNECT on channel:" + channel
                        + " : " + message.getClass() + " : "
                        + message.toString());
            }

            @Override
            public void reconnectCallback(String channel, Object message) {
                super.reconnectCallback(channel, message);
                System.out.println("SUBSCRIBE : RECONNECT on channel:" + channel
                        + " : " + message.getClass() + " : "
                        + message.toString());
            }

            @Override
            public void disconnectCallback(String channel, Object message) {
                super.disconnectCallback(channel, message);
                System.out.println("SUBSCRIBE : DISCONNECT on channel:" + channel
                        + " : " + message.getClass() + " : "
                        + message.toString());
            }
        };
    }

    public static void publish(String channel, JSONObject message) {
        mPubnub.publish(channel, message, mPubnubCallback);
    }

    public static void subscribe(String channel) {
        try {
            mPubnub.subscribe(channel, mPubnubCallback);
        } catch(PubnubException e) {
            System.out.println("SUBSCRIBE : EXCEPTION on channel:" + channel);
        }
    }

    public static void unsubscribeAll() {
        mPubnub.unsubscribeAll();
    }
}
