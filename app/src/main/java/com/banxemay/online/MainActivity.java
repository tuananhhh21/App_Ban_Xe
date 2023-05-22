package com.banxemay.online;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static List<Model> modelList;
    public static int tongTien;
    RecyclerView recyclerView;
    OrderAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        modelList = new ArrayList<>();
        modelList.add(new Model("SH CBS 29N-713DB", 72500000, "Honda SH CBS, màu xanh đen, dung tích 125cc, xe có khóa smartkey thông minh, an toàn, hiện đại, xe đẹp xuất sắc, đi cực ít." +
                " SH là dòng xe tay ga cao cấp của hãng xe máy nổi tiếng Honda.", R.drawable.anh1 ));
        modelList.add(new Model("Elizabeth Fi 20B-327EK", 7300000,"Attila Elizabeth Fi, màu vàng, dung tích 125cc. Đây là dòng xe có thiết kế nữ tính, sang trọng, nhiều chi tiết tinh tế, " +
                "kiểu dáng trang nhã, thời trang hiện đại.", R.drawable.anh2));
        modelList.add(new Model("Vision 37M-338FB", 28000000,"Honda Vision, màu đỏ đen, dung tích 110cc, xe đẹp lung linh, không một lỗi nhỏ. Đây là dòng xe ga có thiết kế nhỏ nhắn, nhẹ nhàng.", R.drawable.anh3));
        modelList.add(new Model("Liberty ie 29P-070HE", 12600000,"Piaggio Liberty ie, màu xanh, dung tích 125cc, đi rất ít, xe đẹp xuất sắc, máy chất, chạy êm, cực kỳ tiết kiệm nhiên liệu.", R.drawable.anh4));
        modelList.add(new Model("SH CBS 36C-392HC", 74500000,"Honda SH CBS, màu đen, dung tích 150cc, xe có khóa smartkey thông minh, an toàn, hiện đại, xe đẹp xuất sắc, đi cực ít.", R.drawable.anh5));
        modelList.add(new Model("SH CBS 29S-092CF", 76300000,"Honda SH CBS, màu trắng đen, dung tích 125cc, xe có khóa smartkey thông minh, an toàn, hiện đại, xe đẹp xuất sắc, đi cực ít. SH là dòng " +
                "xe tay ga cao cấp của hãng xe máy nổi tiếng Honda.", R.drawable.anh6));
        modelList.add(new Model("Vision 29S-779JC", 29000000,"Honda Vision, màu đỏ đen, dung tích 110cc, xe đẹp lung linh, không một lỗi nhỏ. Đây là dòng xe ga có thiết kế nhỏ nhắn, nhẹ nhàng.", R.drawable.anh7));
        modelList.add(new Model("Lead 29C-505EJ", 21800000,"Honda Lead, khóa chìa, màu đỏ đen, dung tích 125cc. Lead là một trong những dòng xe đa dụng, phù hợp với thị hiếu của nhiều người tiêu dùng.", R.drawable.anh8));
        modelList.add(new Model("SH ABS 89B-185BG", 86300000,"Honda SH ABS, màu trắng đen, dung tích 150cc, xe có khóa smartkey thông minh, an toàn, hiện đại, xe đẹp xuất sắc, đi cực ít.", R.drawable.anh9));
        modelList.add(new Model("Lead 29B-129EJ", 14300000,"Honda Lead, màu nâu đen, dung tích 110cc. Lead là một trong những dòng xe đa dụng, phù hợp với thị hiếu của nhiều người tiêu dùng.", R.drawable.anh10));

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(null));
        mAdapter = new OrderAdapter(this, modelList);
        recyclerView.setAdapter(mAdapter);
    }
}