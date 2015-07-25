package kr.edcan.grooshbene.appjamnojam;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.kakao.KakaoLink;
import com.kakao.KakaoParameterException;
import com.kakao.KakaoTalkLinkMessageBuilder;

import java.util.ArrayList;

/**
 * Created by grooshbene on 15. 7. 25.
 */
public class Home extends Activity {
        Button button;
    KakaoLink kakaoLink;
    KakaoTalkLinkMessageBuilder kakaoTalkLinkMessageBuilder;
    FrameLayout frameLayout;
    SharedPreferences pref1;
    SharedPreferences pref2;
    SharedPreferences.Editor edit1;
    SharedPreferences.Editor edit2;
    ImageView setOn;
    ImageView setOff;
    FloatingActionButton plus_btn;
    LinearLayout backbtn;
    int cnt;
    int SetCnt;
    ArrayList<CData> dataArr;
    ListView Listv;
    ArrayAdapter<CData> mAdapter;
    Intent intent_plus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        backbtn = (LinearLayout)findViewById(R.id.backbtn);
        pref1 = getSharedPreferences("content",0);
        pref2 = getSharedPreferences("count", 0);
        edit1 = pref1.edit();
        edit2 = pref2.edit();
        setOn = (ImageView)findViewById(R.id.toggle_lock);
        setOff = (ImageView)findViewById(R.id.toggle_unlock);
        Listv = (ListView)findViewById(R.id.listv);
        plus_btn = (FloatingActionButton)findViewById(R.id.plus_btn);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //백버튼 온클릭

        plus_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent_plus = new Intent(Home.this, PlusActivity.class);
                startActivityForResult(intent_plus,200);
            }
        });
        //플러스버튼 온클릭

        dataArr = new ArrayList<CData>();
        mAdapter = new DataAdapter(Home.this, dataArr);
        Listv.setAdapter(mAdapter);
        initList();

        //리스트뷰

        SetCnt = pref2.getInt("SetCnt", 0);
        switch (SetCnt%2) {
            case 1:
                setOn.setVisibility(ImageView.INVISIBLE);
                setOff.setVisibility(ImageView.VISIBLE);
                break;
            case 0:
                setOn.setVisibility(ImageView.VISIBLE);
                setOff.setVisibility(ImageView.INVISIBLE);
                break;
        }
//                토글 불러오기

        frameLayout = (FrameLayout)findViewById(R.id.btn_set);
        frameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetCnt++;
                switch (SetCnt%2) {
                    case 1:
                        setOn.setVisibility(ImageView.INVISIBLE);
                        setOff.setVisibility(ImageView.VISIBLE);
                        break;
                    case 0:
                        setOn.setVisibility(ImageView.VISIBLE);
                        setOff.setVisibility(ImageView.INVISIBLE);
                        break;
                }
                edit2.putInt("SetCnt",SetCnt);
                edit2.commit();
            }
        });

        //토글

//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                try {
//                    kakaoLink = KakaoLink.getKakaoLink(getApplicationContext());
//                    kakaoTalkLinkMessageBuilder = kakaoLink.createKakaoTalkLinkMessageBuilder();
//                    kakaoTalkLinkMessageBuilder.addText("널 지목하였다");
//                    kakaoLink.sendMessage(kakaoTalkLinkMessageBuilder.build(), Home.this);
//                } catch (KakaoParameterException e) {
//                    Log.e("error", e.getMessage());
//                }
//            }
//        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 200){
            initList();
        }
    }

    public void initList(){
        mAdapter.clear();
        cnt = pref2.getInt("count",0);
        for(int i=0;i<=cnt;i++) {
            String name = pref1.getString("name" + i, "");
            Log.d("", "name : " + name + ", count : " + cnt);
            if( name.isEmpty())
                continue;
            mAdapter.add(new CData(getApplicationContext(), name));
        }
        mAdapter.notifyDataSetChanged();
    }
}
