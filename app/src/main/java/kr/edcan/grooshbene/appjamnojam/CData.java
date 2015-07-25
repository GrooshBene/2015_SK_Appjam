package kr.edcan.grooshbene.appjamnojam;

import android.content.Context;

/**
 * Created by grooshbene on 15. 7. 25.
 */
public class CData {
    public String content_label;
    public String description;
    public String phone;
    public int spCnt;

    public CData(Context context,String phone_) {
        phone = phone_;
    }


    public String getContent_label() {
        return content_label;
    }

    public String getDescription() {
        return description;
    }

    public String getPhone() {
        return phone;
    }

    public int getSpCnt(){return spCnt;}

}