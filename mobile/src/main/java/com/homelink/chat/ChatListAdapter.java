package com.homelink.chat;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.homelink.R;

import org.apache.commons.logging.Log;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by abhijit on 7/6/15.
 */
public class ChatListAdapter extends BaseAdapter {

    private Context context;
    private List<JSONObject> mChatItems;

    public ChatListAdapter(Context context, List<JSONObject> chatItems) {
        this.context = context;
        this.mChatItems = chatItems;
    }

    @Override
    public int getCount() {
        return mChatItems.size();
    }

    @Override
    public Object getItem(int position) {
        return mChatItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        /**
         * The following list not implemented reusable list items as list items
         * are showing incorrect data Add the solution if you have one
         * */

        JSONObject m = mChatItems.get(position);

        String nodeId = null;
        String msg = null;
        try {
            nodeId = m.getString("nodeId");
            msg = m.getString("msg");
        } catch(JSONException e) {
            nodeId = null;
            msg = null;
            return null;
        }

        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);


        // Identifying the message owner
        if((nodeId.equals("100"))) {
            // message belongs to you, so load the right aligned layout
            convertView = mInflater.inflate(R.layout.list_item_message_right,
                    null);
        } else {
            // message belongs to other person, load the left aligned layout
            convertView = mInflater.inflate(R.layout.list_item_message_left,
                    null);
        }

        TextView lblFrom = (TextView) convertView.findViewById(R.id.lblMsgFrom);
        TextView txtMsg = (TextView) convertView.findViewById(R.id.txtMsg);

        txtMsg.setText(msg);
        lblFrom.setText(nodeId);

        return convertView;
    }
}
