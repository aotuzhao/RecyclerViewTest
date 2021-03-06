package com.zhao.recyclerviewtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<String> list;
    private RecyAdapter recyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        recyclerView = (RecyclerView) findViewById(R.id.id_recyclerview);
        LinearLayoutManager layout = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layout);
        recyAdapter = new RecyAdapter();
        recyclerView.setAdapter(recyAdapter);
        recyclerView.addItemDecoration(new DividerItemDectation(this, DividerItemDectation.VERTICAL_LIST));

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                Log.e("Recy状态变化newsState----",newState+"");
                if (recyclerView.canScrollVertically(1)==false&&recyclerView.canScrollVertically(-1)==true){
                    Log.e("Recy到达底部newsState----","进行加载更多操作");
                }
                if (recyclerView.canScrollVertically(1)==true&&recyclerView.canScrollVertically(-1)==false){
                    Log.e("Recy到达顶部newsState----","进行刷新操作----刷新建议使用SwipeRefresh");
                }

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                Log.e("Recy滑动变化----","dx:"+dx+" dy:"+dy);
                Log.e("Recy滑动变化----","向上:"+recyclerView.canScrollVertically(1));
                Log.e("Recy滑动变化----","向下:"+recyclerView.canScrollVertically(-1));
            }
        });

    }

    private void initData() {
        list = new ArrayList<String>();
        for (int i = 'A'; i < 'Z'; i++) {
            list.add("" + (char) i);
        }
    }

    class RecyAdapter extends RecyclerView.Adapter<RecyAdapter.ViewHolder> {


        class ViewHolder extends RecyclerView.ViewHolder {

            TextView tv;

            public ViewHolder(View itemView) {
                super(itemView);
                tv = (TextView) itemView.findViewById(R.id.id_num);
            }
        }


        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.item, parent, false);
            ViewHolder viewHolder = new ViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.tv.setText(list.get(position));
        }

        @Override
        public int getItemCount() {

            return list.size();
        }


    }

}

