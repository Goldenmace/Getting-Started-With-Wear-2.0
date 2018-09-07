package com.gmits.gettingstarted;

import android.support.annotation.NonNull;
import android.support.wearable.view.WearableRecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class CustomRecyclerAdapter extends WearableRecyclerView.Adapter<CustomRecyclerAdapter.ViewHolderA> {

    public OnItemClick onItemClick;

    private int[] drawables = new int[]{
            R.drawable.color_red,
            R.drawable.color_blue,
            R.drawable.color_grey,
            R.drawable.color_orange,
            R.drawable.color_neon,
            R.drawable.color_pink
    };

    private String[] colorStrings = new String[]{
            "Red",
            "Blue",
            "Grey",
            "Orange",
            "Neon",
            "Pink"
    };

    private String[] mColors = new String[]{
            "#FFFF3232",
            "#FF385CDE",
            "#b9b9b9",
            "#fc8e4e",
            "#51d6a7",
            "#d151d6"
    };

    public void setOnItemSelectListener(OnItemClick onItemClick){
        this.onItemClick = onItemClick;
    }


    public interface OnItemClick{
        void onItemColorSelect(String colorCode);
    }


    @NonNull
    @Override
    public ViewHolderA onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        return new ViewHolderA(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_wear_list, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderA viewHolder, final int position) {

        viewHolder.ivView.setBackgroundResource(drawables[position]);
        viewHolder.tvName.setText(colorStrings[position]);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClick.onItemColorSelect(mColors[position]);
            }
        });

    }

    @Override
    public int getItemCount() {
        return drawables.length;
    }

    public static class ViewHolderA extends WearableRecyclerView.ViewHolder {

        private View ivView;
        private TextView tvName;

        public ViewHolderA(View view) {
            super(view);

            ivView = view.findViewById(R.id.ivImage);
            tvName = view.findViewById(R.id.tvName);
        }

    }

}