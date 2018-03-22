package jana.g2evolution.dynamicradiobuttonsinrecyclerview.Radio;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
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


/**
 * Created by Jana on 3/21/2017.
 */
public class FridayDetails_Adapter extends RecyclerView.Adapter<FridayDetails_ViewHolderFeed> {

    ArrayList<HashMap<String, String>> arraylist;
    JSONParser jsonParser = new JSONParser();
    Context context;
    private PersonAdapter adapter;
    private List<Person> feedItemList = new ArrayList<Person>();
    private RecyclerView mRecyclerView;


    private ArrayList<FridayDetails_List> mListFeeds;
    private List<Person> mListFeeds2;
    private LayoutInflater mInflater;
    private int mPreviousPosition = 0;
    private Context mContext;

    String Pid,name,company,price,photo;
    String rating;

    int pos;
    String qty,rowid, radioid;
    private OnItemClick mCallback;



    public FridayDetails_Adapter(Context context, ArrayList<FridayDetails_List> feedList, List<Person> feedList2, OnItemClick listener){
       // mContext = context;
        // mInflater = LayoutInflater.from(context);
        //mListFeeds=feedList;

        this.mListFeeds2 = feedList2;
        this.mListFeeds = feedList;
        this.mContext = context;

        this.mCallback = listener;



    }

    public interface OnItemClick {
        void onClickedItem(int pos, int qty);
    }
    public void setItemClickCallback(final OnItemClick mCallback) {
        this.mCallback = mCallback;
    }





   /* public void setData( ArrayList<FridayDetails_List> feedList){
        mListFeeds=feedList;
        notifyDataSetChanged();
    }*/


    @Override
    public FridayDetails_ViewHolderFeed onCreateViewHolder(ViewGroup parent, int viewType) {


        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.tab_weeklyaddons, null);
        FridayDetails_ViewHolderFeed rcv = new FridayDetails_ViewHolderFeed(layoutView);
        return rcv;
    }


    @Override
    public void onBindViewHolder(final FridayDetails_ViewHolderFeed holder, final  int i) {
        final FridayDetails_List feederInfo = mListFeeds.get(i);

        final Person personInfo = mListFeeds2.get(i);
        String feedDesc = null;


        holder.Biriyaninon.setText(feederInfo.getBiriyaninon());
        holder.Biriyanispecification.setText(feederInfo.getSpecification());





        int buttons = i;
        Log.e("testing","button id = "+buttons);
        for (int i1 = 1; i1 <= buttons ; i1++) {
            RadioButton rbn = new RadioButton(mContext);
            //rbn.setId(i1);
            rbn.setText(personInfo.getmName());
            holder.Radiogroup1.addView(rbn);
        }





        //  holder.rate.setRating(feederInfo.get_rating());

        holder.Increment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                int currentNos = Integer.parseInt(holder.Count.getText().toString());
                holder.Count.setText(String.valueOf(++currentNos));




                qty = holder.Count.getText().toString();
                rowid =     feederInfo.getId();

                //mSelectedItem = getAdapterPosition();

                //OnItemClick mCallback = new  OnItemClick();
                //mCallback = new OnItemClick();

                Log.e("qty", "qty ===== " + qty);
                //mCallback.onClick(qty);

                if (mCallback!=null){
                    mCallback.onClickedItem(i, Integer.parseInt(qty));
                }

            }
        });

        holder.Decrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                int currentNos = Integer.parseInt(holder.Count.getText().toString());
                holder.Count.setText(String.valueOf(--currentNos));

                qty = holder.Count.getText().toString();

                rowid = feederInfo.getId();

                if (mCallback != null) {
                    mCallback.onClickedItem(i, Integer.parseInt(qty));
                }

                Log.e("qty", "qty ===== " + qty);

            }
        });



        holder.Biriyanispecification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        //  holder.rate.setRating(feederInfo.get_rating());



        mPreviousPosition = i;

    }

    @Override
    public int getItemCount() {
        return mListFeeds.size();
    }



}