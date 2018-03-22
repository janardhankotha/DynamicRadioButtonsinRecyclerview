package jana.g2evolution.dynamicradiobuttonsinrecyclerview.Radio;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import jana.g2evolution.dynamicradiobuttonsinrecyclerview.R;


/**
 * Created by Jana on 3/21/2017.
 */
public class FridayDetails_ViewHolderFeed extends RecyclerView.ViewHolder implements View.OnClickListener {



    public TextView Biriyaninon;
    public TextView Biriyanispecification;
    public TextView Biriyanimoney;
    public ImageView Increment;
    public ImageView Decrement;
    public TextView Count;
    public RadioGroup Radiogroup1;


    public FridayDetails_ViewHolderFeed(View itemView) {

        super(itemView);
        itemView.setOnClickListener(this);

        Biriyanispecification = (TextView)itemView.findViewById(R.id.chinesespecification);
        Biriyaninon = (TextView)itemView.findViewById(R.id.chinesenon);
        Count=(TextView)itemView.findViewById(R.id.quantity_text_view1);
        Increment = (ImageView) itemView.findViewById(R.id.butincrement1);
        Decrement = (ImageView)itemView.findViewById(R.id.butdecrement1);
        Radiogroup1 = (RadioGroup) itemView.findViewById(R.id.Radiogroup1);

    }

    @Override
    public void onClick(View view) {
        //Toast.makeText(view.getContext(), "Clicked Position = " + getPosition(), Toast.LENGTH_SHORT).show();

        Integer valueinteger = getPosition();
        String positionvalue = String.valueOf(valueinteger);
        positionvalue.equals("number");
    }

}
