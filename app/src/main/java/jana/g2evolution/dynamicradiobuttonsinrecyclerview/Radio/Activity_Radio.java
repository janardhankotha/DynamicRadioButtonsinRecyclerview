package jana.g2evolution.dynamicradiobuttonsinrecyclerview.Radio;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import jana.g2evolution.dynamicradiobuttonsinrecyclerview.JSONParser;
import jana.g2evolution.dynamicradiobuttonsinrecyclerview.PersonAdapter;
import jana.g2evolution.dynamicradiobuttonsinrecyclerview.R;

public class Activity_Radio extends AppCompatActivity implements FridayDetails_Adapter.OnItemClick{

    ArrayList<HashMap<String, String>> arraylist;
    JSONParser jsonParser = new JSONParser();
    Context context;
    private PersonAdapter adapter;
    private List<Person> feedItemList = new ArrayList<Person>();
    private RecyclerView mRecyclerView;
    private FridayDetails_Adapter mAdapterFeeds;
    private FridayDetails_Adapter.OnItemClick mCallback;
    private ArrayList<FridayDetails_List> allItems1 = new ArrayList<FridayDetails_List>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio);

        context = this;

        mRecyclerView = (RecyclerView)findViewById(R.id.feedrecycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));

        mCallback=this;

        ConnectionDetector cd = new ConnectionDetector(context);
        if (cd.isConnectingToInternet()) {



            new LoadSpinnerdata().execute();


        } else {


            Toast.makeText(context, "Internet Connection Not Available Enable Internet And Try Again", Toast.LENGTH_LONG).show();

        }

    }

    public void onClickedItem(int pos, int qty) {
        Log.e("DMen", "Pos:"+ pos + "Qty:"+qty);
        allItems1.get(pos).setCartCount(qty);
    }




    class LoadSpinnerdata extends AsyncTask<String, String, String> {
        String status;
        String responce;

        private ProgressDialog pDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(context);
            pDialog.setMessage("Loading Services");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();

        }

        public String doInBackground(String... args) {



            List<NameValuePair> userpramas = new ArrayList<NameValuePair>();


            Log.e("testing", "productid value=" + "146");
            userpramas.add(new BasicNameValuePair("productid", "146"));
            JSONObject json = jsonParser.makeHttpRequest("http://www.ahilgroup.com/app/menu.php", "POST", userpramas);

         Log.e("testing", "jsonParser" + json);


            if (json == null) {

            Log.e("testing", "jon11111111111111111");
            // Toast.makeText(getActivity(),"Data is not Found",Toast.LENGTH_LONG);

            return responce;
        } else {
            Log.e("testing", "jon2222222222222");
            // DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                status = json.getString("status");
                JSONObject response = new JSONObject(json.toString());

                Log.e("testing", "jsonParser2222" + json);

                //JSONObject jsonArray1 = new JSONObject(json.toString());
                // Result = response.getString("status");
                JSONArray posts = response.optJSONArray("categories");
                Log.e("testing", "jsonParser3333" + posts);

                if (posts == null) {
                    Log.e("testing", "jon11111111111111111");

                    //Toast.makeText(getContext(),"No data found",Toast.LENGTH_LONG).show();
                } else {

                    Log.e("testing", "jon122222211");
                    Log.e("testing", "jsonParser4444" + posts);

                    for (int i = 0; i < posts.length(); i++) {
                        Log.e("testing", "" + posts);

                        Log.e("testing", "" + i);
                        JSONObject post = posts.optJSONObject(i);

                        FridayDetails_List item = new FridayDetails_List();

                        item.setId(post.optString("cat_name"));
                        item.setSpecification(post.optString("cat_name"));
                        item.setTextrs(post.optString("cat_name"));
                        //item.setQuestion(post.optString("price"));

                        allItems1.add(item);






                      /*      JSONArray posts2 = post.optJSONArray("image_gallery");
                            for (int i1 = 0; i1 < posts2.length(); i1++) {
                                JSONObject post2 = posts2.optJSONObject(i1);

                                productimage = post2.getString("slider_image");


                                Log.e("testing","image"+post2.getString("slider_image"));

                                //find the group position inside the list
                                //groupPosition = deptList.indexOf(headerInfo);
                            }*/

                        JSONArray posts3 = post.optJSONArray("categories");
                        for (int i2 = 0; i2 < posts3.length(); i2++) {
                            JSONObject post3 = posts3.optJSONObject(i2);

                            //   HashMap<String, String> map = new HashMap<String, String>();


                            Person item2 = new Person();
                            item2.setMpid(post3.optString("category_id"));
                            item2.setmName(post3.optString("cat_name"));
                            item2.setMprice(post3.optString("type"));
                            item2.setMdiscount(post3.optString("type"));

                            Log.e("testing","packname = "+post.optString("packname"));
                            Log.e("testing","price = "+post.optString("price"));
                            Log.e("testing","discount = "+post.optString("discount"));

                            feedItemList.add(item2);


                            // Log.e("testing", currentobj.getString("services"));

                            //  arraylist.add(map);

                            // productimage = post3.getString("slider_image");


                            //Log.e("testing","image"+post3.getString("slider_image"));

                            //find the group position inside the list
                            //groupPosition = deptList.indexOf(headerInfo);
                        }

                    }



                }


            } catch (JSONException e) {
                e.printStackTrace();
            }


            return responce;
        }


    }



        @Override
        protected void onPostExecute(String responce) {
            super.onPostExecute(responce);
            pDialog.dismiss();
            Log.e("testing", "result is = " + responce);

            mAdapterFeeds = new FridayDetails_Adapter(context, allItems1,feedItemList, mCallback);
            mRecyclerView.setAdapter(mAdapterFeeds);

            mAdapterFeeds.notifyDataSetChanged();

        }

    }
}
