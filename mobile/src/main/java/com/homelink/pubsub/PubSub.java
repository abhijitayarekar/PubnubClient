package com.homelink.pubsub;

import com.pubnub.api.Callback;
import com.pubnub.api.Pubnub;
import com.pubnub.api.PubnubError;
import com.pubnub.api.PubnubException;

import org.json.JSONObject;

/**
 * Created by abhijit on 7/6/15.
 */
public abstract class PubSub {

    protected static PubSub mPubSubInstance;
    protected static PubSubListener mListener;

    public static PubSub init() {
        if(mPubSubInstance == null) mPubSubInstance = createInstance();
        return mPubSubInstance;
    }

    private static PubSub createInstance() {
        return null;
    }

    protected PubSub() {
        mListener = null;
    }

    public static void listen(PubSubListener l) {
        mListener = l;
    }

    public static void publish(String channel, JSONObject message) {
    }

    public static void subscribe(String channel) {
    }

    public static void unsubscribeAll() {

    }

    public static interface PubSubListener {
        public void onMessage(String channel, JSONObject message);
        public void onPublish(String channel, JSONObject message);
    }
}
