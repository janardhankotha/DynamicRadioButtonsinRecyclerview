package jana.g2evolution.dynamicradiobuttonsinrecyclerview;

/**
 * Created by Jana on 5/30/2016.
 */

import android.content.Context;
import android.text.Html;
import android.util.Log;
import android.view.View;

import java.util.List;

import jana.g2evolution.dynamicradiobuttonsinrecyclerview.Radio.Person;


public class PersonAdapter extends RadioAdapter<Person> {
    public PersonAdapter(Context context, List<Person> items) {
        super(context, items);
    }

    @Override
    public void onBindViewHolder(RadioAdapter.ViewHolder viewHolder, int i) {
        super.onBindViewHolder(viewHolder, i);


        //viewHolder.mText.setText(mItems.get(i).mName);


        viewHolder.mpid.setText(mItems.get(i).getMpid());
        viewHolder.mText.setText(mItems.get(i).getmName());
        viewHolder.mprice.setText(mItems.get(i).getMprice());
        viewHolder.mdiscount.setText(mItems.get(i).getMdiscount());

        if (mItems.get(i).getMdiscount().equals("0")||mItems.get(i).getMdiscount() == null){
            viewHolder.mdiscount.setVisibility(View.GONE);
        }else {
            viewHolder.mdiscount.setText("-" + Html.fromHtml(mItems.get(i).getMdiscount()) + "%");
        }

        Log.e("tesing", "name in adapter " + (mItems.get(i).getmName()));
    }
}