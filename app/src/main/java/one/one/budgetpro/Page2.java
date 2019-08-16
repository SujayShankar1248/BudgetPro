package one.one.budgetpro;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import java.util.ArrayList;
import android.widget.ArrayAdapter;
import android.view.View;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.content.ContentValues;

import com.one.budgetpro.R;


public class Page2 extends AppCompatActivity implements View.OnClickListener{
    private TextView mTextMessage;
    private Button bt1,bt2,bt3,bt4;
    private EditText etxt1,etxt2,etxt3,etxt4;
    private ListView lv1,lv2,lv3,lv4;
    private InputDbHelper mHelper,mHelper2,mHelper3,mHelper4;


    ArrayList<String>l1=new ArrayList<String>();
    ArrayList<String>l2=new ArrayList<String>();
    ArrayList<String>l3=new ArrayList<String>();
    ArrayList<String>l4=new ArrayList<String>();
    ArrayAdapter<String>ad1;
    ArrayAdapter<String>ad2;
    ArrayAdapter<String>ad3;
    ArrayAdapter<String>ad4;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page2);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        bt1 = (Button) findViewById(R.id.button);
        bt1.setOnClickListener(this);
        etxt1 = (EditText)findViewById(R.id.editText3);
        lv1=(ListView)findViewById(R.id.list_view);
        mHelper = new InputDbHelper(this,"db1");
        updateUI(lv1,ad1,l1,mHelper);


        bt2=(Button)findViewById(R.id.button2);
        bt2.setOnClickListener(this);
        etxt2 = (EditText)findViewById(R.id.editText4);
        lv2=(ListView)findViewById(R.id.list_view2);
        mHelper2 = new InputDbHelper(this,"db2");
        updateUI(lv2,ad2,l2,mHelper2);


        bt3=(Button)findViewById(R.id.button3);
        bt3.setOnClickListener(this);
        etxt3 = (EditText)findViewById(R.id.editText5);
        lv3=(ListView)findViewById(R.id.list_view3);
        mHelper3 = new InputDbHelper(this,"db3");
        updateUI(lv3,ad3,l3,mHelper3);

        bt4=(Button)findViewById(R.id.button4);
        bt4.setOnClickListener(this);
        etxt4 = (EditText)findViewById(R.id.editText6);
        lv4=(ListView)findViewById(R.id.list_view4);
        mHelper4=new InputDbHelper(this,"db4");
        updateUI(lv4,ad4,l4,mHelper4);


    }

    public void onClick(View x){
        switch(x.getId()){
            case R.id.button:
                String input=etxt1.getText().toString();
                if(input.length()>0) {
                    SQLiteDatabase db = mHelper.getWritableDatabase();
                    ContentValues values = new ContentValues();
                    values.put(InputContract.TaskEntry.COL_TASK_TITLE, input);
                    db.insertWithOnConflict(InputContract.TaskEntry.TABLE, null, values, SQLiteDatabase.CONFLICT_REPLACE);
                    db.close();
                    updateUI(lv1, ad1, l1,mHelper);
                }
                break;
            case R.id.button2:
                String input2=etxt2.getText().toString();
                if(input2.length()>0) {
                    SQLiteDatabase db = mHelper2.getWritableDatabase();
                    ContentValues values = new ContentValues();
                    values.put(InputContract.TaskEntry.COL_TASK_TITLE, input2);
                    db.insertWithOnConflict(InputContract.TaskEntry.TABLE, null, values, SQLiteDatabase.CONFLICT_REPLACE);
                    db.close();
                    updateUI(lv2, ad2, l2,mHelper2);
                }
                break;
            case R.id.button3:
                String input3=etxt3.getText().toString();
                if(input3.length()>0){
                    SQLiteDatabase db = mHelper3.getWritableDatabase();
                    ContentValues values = new ContentValues();
                    values.put(InputContract.TaskEntry.COL_TASK_TITLE, input3);
                    db.insertWithOnConflict(InputContract.TaskEntry.TABLE, null, values, SQLiteDatabase.CONFLICT_REPLACE);
                    db.close();
                    updateUI(lv3, ad3, l3,mHelper3);
                }
                break;
            case R.id.button4:
                String input4=etxt4.getText().toString();
                if(input4.length()>0){
                    SQLiteDatabase db = mHelper4.getWritableDatabase();
                    ContentValues values = new ContentValues();
                    values.put(InputContract.TaskEntry.COL_TASK_TITLE, input4);
                    db.insertWithOnConflict(InputContract.TaskEntry.TABLE, null, values, SQLiteDatabase.CONFLICT_REPLACE);
                    db.close();
                    updateUI(lv4, ad4, l4,mHelper4);
                }
                break;
        }
    }


    private void updateUI(ListView x, ArrayAdapter<String>y,ArrayList<String>z,InputDbHelper m) {
        ArrayList<String> taskList = new ArrayList<>();
        SQLiteDatabase db = m.getReadableDatabase();

        Cursor cursor = db.query(InputContract.TaskEntry.TABLE,
                new String[]{InputContract.TaskEntry._ID, InputContract.TaskEntry.COL_TASK_TITLE},
                null, null, null, null, null);
        while (cursor.moveToNext()) {
          int idx = cursor.getColumnIndex(InputContract.TaskEntry.COL_TASK_TITLE);
          taskList.add(cursor.getString(idx));
        }
        if (y== null) {
            y= new ArrayAdapter<>(this,  android.R.layout.simple_expandable_list_item_1, taskList);
            x.setAdapter(y);

        }
        else {
            y.clear();
            y.addAll(z);
            y.notifyDataSetChanged();
        }
        cursor.close();
        db.close();
    }


}
