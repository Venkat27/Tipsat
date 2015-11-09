package com.tipstat.hackerearth.tipstat;

import android.app.ProgressDialog;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public int TOTAL_LIST_ITEMS = 250;
    public int NUM_ITEMS_PAGE = 10;
    public int API_HITS = 0;
    ListView listView;
    List<Member> membersList;
    CustomAdapter adapter;
    boolean isAPIHit = false;
    Typeface iconFont;
    private int noOfBtns;
    //private Button[] btns;
    private TextView[] btns;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iconFont = FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOME);
        FontManager.markAsIconContainer(findViewById(R.id.tv_icon_container), iconFont);
        FontManager.markAsIconContainer(findViewById(R.id.tv_icon_container_ethnicity), iconFont);
        FontManager.markAsIconContainer(findViewById(R.id.tv_icon_container_star), iconFont);

        Spinner spinner = (Spinner) findViewById(R.id.spinner_ethnicity);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.arr_ethnicity, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        SearchView searchView = (SearchView) findViewById(R.id.searchView);
        searchView.setIconifiedByDefault(false);
        searchView.setQueryHint("Search by (Status, weight, Height)");

        //*** setOnQueryTextFocusChangeListener ***
        /*searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                // TODO Auto-generated method stub

                Toast.makeText(getBaseContext(), String.valueOf(hasFocus),
                        Toast.LENGTH_SHORT).show();
            }
        });*/

        //*** setOnQueryTextListener ***
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                // TODO Auto-generated method stub

                Toast.makeText(getBaseContext(), query,
                        Toast.LENGTH_SHORT).show();

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // TODO Auto-generated method stub

                Toast.makeText(getBaseContext(), newText,
                        Toast.LENGTH_SHORT).show();
                return false;
            }
        });


        listView = (ListView) findViewById(R.id.listView);
        new getMembersforPageTask().execute(1);
        Btnfooter();
        FontManager.markAsIconContainer(findViewById(R.id.tv_icon_sortby), iconFont);
        FontManager.markAsIconContainer(findViewById(R.id.tv_icon_container_weight), iconFont);
        FontManager.markAsIconContainer(findViewById(R.id.tv_icon_height), iconFont);

    }

    private void Btnfooter() {
        int val = TOTAL_LIST_ITEMS % NUM_ITEMS_PAGE;
        val = val == 0 ? 0 : 1;
        noOfBtns = TOTAL_LIST_ITEMS / NUM_ITEMS_PAGE + val;

        LinearLayout ll = (LinearLayout) findViewById(R.id.btnLay);
        ll.removeAllViews();
        //btns    =new Button[noOfBtns];
        btns = new TextView[noOfBtns];

        for (int i = 0; i < noOfBtns; i++) {
            btns[i] = new TextView(this);
            btns[i].setBackgroundColor(getResources().getColor(android.R.color.transparent));
            btns[i].setText("" + (i + 1));
            btns[i].setPadding(15, 5, 15, 5);
            btns[i].setTextColor(getResources().getColor(R.color.grey));
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(AbsListView.LayoutParams.WRAP_CONTENT, AbsListView.LayoutParams.WRAP_CONTENT);
            ll.addView(btns[i], lp);
            View v = new View(MainActivity.this);
            v.setBackgroundColor(getResources().getColor(R.color.border_green));
            LinearLayout.LayoutParams vlp = new LinearLayout.LayoutParams(2, AbsListView.LayoutParams.MATCH_PARENT);
            ll.addView(v, vlp);

            final int j = i;
            btns[j].setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {
                    new getMembersforPageTask().execute(j);
                    CheckBtnBackGroud(j);
                }
            });

        }

    }

    /**
     * Method for Checking Button Backgrounds
     */
    private void CheckBtnBackGroud(int index) {

        for (int i = 0; i < noOfBtns; i++) {
            if (i == index) {
                btns[index].setBackgroundColor(getResources().getColor(R.color.bg_grey));
                btns[i].setTextColor(getResources().getColor(android.R.color.black));
            } else {
                btns[i].setBackgroundColor(getResources().getColor(android.R.color.transparent));
                btns[i].setTextColor(getResources().getColor(R.color.grey));
            }
        }

    }

    private void bindPageData(List<Member> memberList) {
        adapter = new CustomAdapter(MainActivity.this, memberList);
        listView.setAdapter(adapter);
    }

    private List<Member> initiallizenGetData(int pageNo) {
        isAPIHit = false;
        if (membersList == null) {

            InputStream inputStream = null;
            String membersString = null;
            try {
                inputStream = getResources().openRawResource(R.raw.members_file);
                byte[] reader = new byte[inputStream.available()];
                while (inputStream.read(reader) != -1) {
                }
                membersString = new String(reader);
            } catch (IOException e) {
                Log.e("Reading from File", e.getMessage());
            } finally {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        Log.e("Reading from File", e.getMessage());
                    }
                }
            }

            MemberList members = new Gson().fromJson(membersString, MemberList.class);
            membersList = members.getMembers();
            API_HITS++;
            isAPIHit = true;


        }


        if (pageNo * NUM_ITEMS_PAGE > membersList.size()) {
            Toast.makeText(MainActivity.this, "Invalid page number", Toast.LENGTH_LONG).show();
            return null;
        } else {
            List<Member> pageList = new ArrayList<Member>();
            for (int i = pageNo * NUM_ITEMS_PAGE + 1; i <= ((pageNo + 1) * NUM_ITEMS_PAGE) && i < TOTAL_LIST_ITEMS; i++) {
                pageList.add(membersList.get(i));
            }
            return pageList;
        }


    }

    private class getMembersforPageTask extends AsyncTask<Integer, Void, List<Member>> {

        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            if (progressDialog != null) {
                if (progressDialog.isShowing())
                    progressDialog.dismiss();
                progressDialog = null;
            }
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setMessage("Connecting to Server...");
            progressDialog.show();

        }

        @Override
        protected List<Member> doInBackground(Integer... params) {
            int pageNO = params[0];

            return initiallizenGetData(pageNO);
        }

        @Override
        protected void onPostExecute(List<Member> memberList) {
            super.onPostExecute(memberList);
            if (progressDialog != null) {
                if (progressDialog.isShowing())
                    progressDialog.dismiss();
                progressDialog = null;
            }

            if (memberList != null) {
                bindPageData(memberList);
                if (isAPIHit) {
                    ((LinearLayout) findViewById(R.id.btnLay)).setVisibility(View.VISIBLE);
                }
                ((TextView) findViewById(R.id.tv_total_items)).setText(getText(R.string.total_mem) + " " + TOTAL_LIST_ITEMS);
                ((TextView) findViewById(R.id.tv_api_hits)).setText(getText(R.string.api_hits) + " " + API_HITS);
                FontManager.markAsIconContainer(findViewById(R.id.tv_icon_sortby), iconFont);
                FontManager.markAsIconContainer(findViewById(R.id.tv_icon_container_weight), iconFont);
                FontManager.markAsIconContainer(findViewById(R.id.tv_icon_height), iconFont);


            }
        }
    }


}
