package com.vietthanh.tikidisplaykeyword.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.vietthanh.tikidisplaykeyword.R;
import com.vietthanh.tikidisplaykeyword.model.KeywordModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class KeywordAdapter extends RecyclerView.Adapter<KeywordAdapter.ViewHolder> {

    private List<KeywordModel> mListKeywords;
    private Context mContext;

    public KeywordAdapter(final List<KeywordModel> data, final Context context) {
        mListKeywords = data;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.keyword_row_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        KeywordModel model = mListKeywords.get(position);

        holder.keyword.setText(model.getKeyword());
        holder.keywordBackground.setCardBackgroundColor(model.getColor());
    }

    @Override
    public int getItemCount() {
        return mListKeywords.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.keyword_background)
        CardView keywordBackground;
        @BindView(R.id.keyword_txt)
        TextView keyword;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
