package jana.g2evolution.dynamicradiobuttonsinrecyclerview;

/**
 * Created by Jana on 5/30/2016.
 */

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.List;

public abstract class RadioAdapter<T> extends RecyclerView.Adapter<RadioAdapter.ViewHolder> {
    public int mSelectedItem = -1;
    public List<T> mItems;
    private Context mContext;

    public RadioAdapter(Context context, List<T> items) {
        mContext = context;
        mItems = items;
    }

    @Override
    public void onBindViewHolder(RadioAdapter.ViewHolder viewHolder, final int i) {
        viewHolder.mRadio.setChecked(i == mSelectedItem);

    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        final View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_item, null);
       // LayoutInflater inflater = LayoutInflater.from(mContext);
       // final View view = inflater.inflate(R.layout.view_item, viewGroup, false);
        return new ViewHolder(view);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public RadioButton mRadio;
        public TextView mText;
        public TextView mprice;
        public TextView mdiscount;
        public TextView mpid;
        String radioitem;

        public ViewHolder(final View inflate) {
            super(inflate);
            mText = (TextView) inflate.findViewById(R.id.text);
            mprice = (TextView) inflate.findViewById(R.id.price);
            mdiscount = (TextView) inflate.findViewById(R.id.discount);
            mpid = (TextView) inflate.findViewById(R.id.textViewpid);
            mRadio = (RadioButton) inflate.findViewById(R.id.radio);

            View.OnClickListener clickListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mSelectedItem = getAdapterPosition();


                    radioitem=mText.getText().toString();


                    SharedPreferences prefuserdata = mContext.getSharedPreferences("productadapter", 0);
                    SharedPreferences.Editor prefeditor = prefuserdata.edit();

                    prefeditor.putString("priceid", "" + mpid.getText().toString());
                    prefeditor.putString("packname", "" + mText.getText().toString());
                    prefeditor.putString("price", "" + mprice.getText().toString());
                    prefeditor.putString("discount", "" + mdiscount.getText().toString());

                    prefeditor.commit();
                    Log.e("testing","pricepack = "+mText.getText().toString());
                    Log.e("testing","pice = "+mprice.getText().toString());
                    Log.e("testing","discount = "+mdiscount.getText().toString());

                    Log.e("radioitem -mahi -------", "radiobutton" + radioitem);

                    notifyItemRangeChanged(0, mItems.size());
                }
            };
            itemView.setOnClickListener(clickListener);
            mRadio.setOnClickListener(clickListener);
        }
    }

}