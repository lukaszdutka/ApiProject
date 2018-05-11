package pl.lukaszdutka.apiproject.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import pl.lukaszdutka.apiproject.R;
import pl.lukaszdutka.apiproject.activities.EmployeeDetailsActivity;
import pl.lukaszdutka.apiproject.apiaccess.ApiEmployee;

/**
 * Created by user on 09.05.2018.
 */

public class ApiItemsAdapter extends RecyclerView.Adapter<ApiItemsAdapter.ViewHolder> {

    private List<ApiEmployee> dataList;

    private Context applicationContext;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public ViewPager viewer;
        public View wholeView;

        public ViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.list_field);
            viewer = view.findViewById(R.id.recipe_item_viewer);
            wholeView = view.findViewById(R.id.api_list_layout);
        }
    }

    public ApiItemsAdapter(List<ApiEmployee> dataList, Context applicationContext) {
        this.dataList = dataList;
        this.applicationContext = applicationContext;
    }

    @Override
    public ApiItemsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.api_list_layout, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ApiItemsAdapter.ViewHolder holder, final int position) {
        holder.name.setText(dataList.get(position).getEmployeeName());
        holder.wholeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context, EmployeeDetailsActivity.class);
                intent.putExtra("rId", dataList.get(position).getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

}
