package kr.edcan.grooshbene.appjamnojam;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

/**
 * Created by grooshbene on 15. 7. 25.
 */
public class PlusActivity extends Activity {
    SharedPreferences pref1;
    SharedPreferences pref2;
    SharedPreferences.Editor edit1;
    SharedPreferences.Editor edit2;
    int cnt;
    String text_name;
    EditText text_edit;
    ImageView ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plus);
        pref1 = getSharedPreferences("content", 0);
        pref2 = getSharedPreferences("count", 0);
        edit1 = pref1.edit();
        edit2 = pref2.edit();
        //프리퍼런스 불러오
        text_edit = (EditText)findViewById(R.id.edittext);
        ok = (ImageView)findViewById(R.id.btn_ok);
        //선언부
        cnt = pref2.getInt("count", 0);
        //카운트값 가져오기
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text_name = text_edit.getText().toString();
                edit1.putString("name"+cnt, text_name);
                edit1.commit();
                edit2.putInt("count", ++cnt);
                edit2.commit();
                Log.d("", "" + cnt);
                finish();
            }
        });
    }
}
