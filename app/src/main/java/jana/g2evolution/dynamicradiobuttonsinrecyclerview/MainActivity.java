package jana.g2evolution.dynamicradiobuttonsinrecyclerview;

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

import jana.g2evolution.dynamicradiobuttonsinrecyclerview.Radio.ConnectionDetector;
import jana.g2evolution.dynamicradiobuttonsinrecyclerview.Radio.Person;

public class MainActivity extends AppCompatActivity {
    ArrayList<HashMap<String, String>> arraylist;
    JSONParser jsonParser = new JSONParser();
    Context context;
    private PersonAdapter adapter;
    private List<Person> feedItemList = new ArrayList<Person>();
    private RecyclerView mRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;

        mRecyclerView = (RecyclerView) findViewById(R.id.feedrecycler);
        /*List<Person> persons = Arrays.asList(
                new Person("Larry"),
                new Person("Moe"),
                new Person("Curly"));
*/
        //mRecyclerView.setAdapter(new PersonAdapter(this, persons));

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        ConnectionDetector cd = new ConnectionDetector(context);
        if (cd.isConnectingToInternet()) {



            new userdata().execute();


        } else {


            Toast.makeText(context, "Internet Connection Not Available Enable Internet And Try Again", Toast.LENGTH_LONG).show();

        }

    }

    public class userdata extends AsyncTask<String, String, String> {
        String responce;
        String message;
        String headers;
        String childs;
        String status;

        String productname, productimage, productcode, productspec, productdesc;

        private ProgressDialog pDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();


        }

        protected String doInBackground(String... args) {

            arraylist = new ArrayList<HashMap<String, String>>();
            Integer result = 0;
            List<NameValuePair> userpramas = new ArrayList<NameValuePair>();


            Log.e("testing", "jsonParser startedkljhk");
            //userpramas.add(new BasicNameValuePair("feader_reg_id", id));
            //  Log.e("testing", "feader_reg_id" + id);

            Log.e("testing", "productid value=" + "146");
            userpramas.add(new BasicNameValuePair("productid", "146"));

            JSONObject json = jsonParser.makeHttpRequest("http://ahilgroup.com/app/product.php", "POST", userpramas);

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
                    JSONArray posts = response.optJSONArray("product");
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

                            productname = post.getString("product_name");
                            productcode = post.getString("productcode");
                            productspec = post.getString("product_specification");
                            productdesc = post.getString("product_description");




                      /*      JSONArray posts2 = post.optJSONArray("image_gallery");
                            for (int i1 = 0; i1 < posts2.length(); i1++) {
                                JSONObject post2 = posts2.optJSONObject(i1);

                                productimage = post2.getString("slider_image");


                                Log.e("testing","image"+post2.getString("slider_image"));

                                //find the group position inside the list
                                //groupPosition = deptList.indexOf(headerInfo);
                            }*/

                            JSONArray posts3 = post.optJSONArray("pricepack");
                            for (int i2 = 0; i2 < posts3.length(); i2++) {
                                JSONObject post3 = posts3.optJSONObject(i2);

                                //   HashMap<String, String> map = new HashMap<String, String>();


                                Person item = new Person();
                                item.setMpid(post3.optString("price_id"));
                                item.setmName(post3.optString("packname"));
                                item.setMprice(post3.optString("price"));
                                item.setMdiscount(post3.optString("discount"));

                                Log.e("testing","packname = "+post.optString("packname"));
                                Log.e("testing","price = "+post.optString("price"));
                                Log.e("testing","discount = "+post.optString("discount"));

                                feedItemList.add(item);


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
        protected void onPostExecute(String result) {
            super.onPostExecute(result);



            if (status.equals("success")){

                adapter = new PersonAdapter(context, feedItemList);
                mRecyclerView.setAdapter(adapter);


              //  product_name.setText(productname);
             //   productdescription = productdesc;
             //   productspecification = productspec;

              /*  if (productcode.equals("0")||productcode == null||productcode.equals("null")){
                    product_code.setVisibility(View.GONE);

                }else{
                    product_code.setText("Product code: "+productcode);
                }
*/

            }else if (status.equals("No")){
                Toast.makeText(context,"no data found",Toast.LENGTH_LONG).show();

            }



/*

            Glide.with(context)
                    .load(Uri.parse(productimage))
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                   // .error(R.drawable.compa4)
                   // .placeholder(R.drawable.compa4)
                    .skipMemoryCache(true)
                    .into(imageprofile);
          pDialog.dismiss();
*/

        }


    }


}
