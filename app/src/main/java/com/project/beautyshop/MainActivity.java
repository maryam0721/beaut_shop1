package com.project.beautyshop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.project.beautyshop.adapter.BasketAdapter;
import com.project.beautyshop.adapter.ViewPagerMainAdapter;
import com.project.beautyshop.database.DatabaseShop;
import com.project.beautyshop.database.ProductDao;
import com.project.beautyshop.listener.UpdateListSearch;
import com.project.beautyshop.model.Product;
import com.project.beautyshop.view.BasketFragment;

import java.util.concurrent.Executor;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigation;
    ViewPager2 viewPagerMain;
    ViewPagerMainAdapter viewPagerMainAdapter;
    //    FloatingActionButton floatingActionButton;
    ProductDao productDao;
    Executor executor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setup();
        if (productDao.getAll().size() == 0) {
            getData(productDao);
        }

//        bottomNavigation.setBackground(null);
        viewPagerMain.setAdapter(viewPagerMainAdapter);
        viewPagerMain.setUserInputEnabled(false);

//        floatingActionButton.setOnClickListener(v -> viewPagerMain.setCurrentItem(2));

        bottomNavigation.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.profile:
                    viewPagerMain.setCurrentItem(0);
                    break;
                case R.id.search:
                    viewPagerMain.setCurrentItem(1);
                    break;
                case R.id.basket:
                    viewPagerMain.setCurrentItem(2);
                    break;
                case R.id.category:
                    viewPagerMain.setCurrentItem(3);
                    break;
                case R.id.home:
                    viewPagerMain.setCurrentItem(4);
                    break;
            }
            return true;
        });
        bottomNavigation.setSelectedItemId(R.id.home);
    }

    public void setup() {
        productDao = DatabaseShop.getAppDataBase(this).productDao();
        bottomNavigation = findViewById(R.id.bottomNavigation);
        viewPagerMain = findViewById(R.id.viewPagerMain);
//        floatingActionButton = findViewById(R.id.floatingActionButton);
        viewPagerMainAdapter = new ViewPagerMainAdapter(getSupportFragmentManager(), this.getLifecycle());
        int a = R.string.titlePink;

    }

    public void getData(ProductDao productDao) {
        //Pink----------------------------------------------------------------------------------
        Product productP1 = new Product(R.drawable.cosmetic_banner,890000, R.drawable.p1, R.string.titlePink, "کرم پودر FAUXFILTER هدی بیوتی",
                R.string.shortDescriptionP1, R.string.longDescriptionP1);
        Product productP2 = new Product(R.drawable.cosmetic_banner,1960000, R.drawable.p2, R.string.titlePink, "پالت سایه Rose Quartz هدی بیوتی",
                R.string.shortDescriptionP2, R.string.longDescriptionP2);
        Product productP3 = new Product(R.drawable.cosmetic_banner,890000, R.drawable.p3, R.string.titlePink, "اسپری فیکس Dewy Set آناستازیا",
                R.string.shortDescriptionP3, R.string.longDescriptionP3);
        Product productP4 = new Product(R.drawable.cosmetic_banner,1040000, R.drawable.p4, R.string.titlePink, "کرم پودر مک STUDIO FIX FLUID",
                R.string.shortDescriptionP4, R.string.longDescriptionP4);
        Product productP5 = new Product(R.drawable.cosmetic_banner,640000, R.drawable.p5, R.string.titlePink, "اسپری فیکس مک  FIX FLUID",
                R.string.shortDescriptionP5, R.string.longDescriptionP5);
        productP1.setRawPrice(950000);
        productDao.add(productP1);
        productDao.add(productP2);
        productDao.add(productP3);
        productDao.add(productP4);
        productDao.add(productP5);
        //Pink----------------------------------------------------------------------------------
        //Yellow--------------------------------------------------------------------------------
        Product productY1 = new Product(R.drawable.skin_banner,390000, R.drawable.y1, R.string.titleYellow, "اسپری آب رطوبت رسان Moisture Surge کلینیک(جدا شده از پک)",
                R.string.shortDescriptionY1, R.string.longDescriptionY1);
        Product productY2 = new Product(R.drawable.skin_banner,730000, R.drawable.y2, R.string.titleYellow, "اسپری ضد آفتاب بدن Transparent ایزدین",
                R.string.shortDescriptionY2, R.string.longDescriptionY2);
        Product productY3 = new Product(R.drawable.skin_banner,950000, R.drawable.y3, R.string.titleYellow, "ضد آفتاب Age Repair فیوژن واتر ایزدین",
                R.string.shortDescriptionY3, R.string.longDescriptionY3);
        Product productY4 = new Product(R.drawable.skin_banner,9500000, R.drawable.y4, R.string.titleYellow, "ست سفت کننده پوست Ritual for Firm Skin تاچاSTUDIO FIX FLUID",
                R.string.shortDescriptionY4, R.string.longDescriptionY4);
        Product productY5 = new Product(R.drawable.skin_banner,990000, R.drawable.y5, R.string.titleYellow, "کرم ترمیم کننده NEOCICA فیلورگا",
                R.string.shortDescriptionY5, R.string.longDescriptionY5);
        productY3.setRawPrice(1200000);
        productDao.add(productY1);
        productDao.add(productY2);
        productDao.add(productY3);
        productDao.add(productY4);
        productDao.add(productY5);
        //Yellow--------------------------------------------------------------------------------
        //Blue----------------------------------------------------------------------------------
        Product productB1 = new Product(R.drawable.perfium_bannner,3200000, R.drawable.b1, R.string.titleBlue, "عطر Alien موگلر",
                R.string.shortDescriptionB1, R.string.longDescriptionB1);
        Product productB2 = new Product(R.drawable.perfium_bannner,5200000, R.drawable.b2, R.string.titleBlue, "عطر Black Afgano ناسوماتو",
                R.string.shortDescriptionB2, R.string.longDescriptionB2);
        Product productB3 = new Product(R.drawable.perfium_bannner,4974000, R.drawable.b3, R.string.titleBlue, "عطر Chance Eau Tendre شنل",
                R.string.shortDescriptionB3, R.string.longDescriptionB3);
        Product productB4 = new Product(R.drawable.perfium_bannner,4920000, R.drawable.b4, R.string.titleBlue, "عطر Coco Mademoiselle شنل",
                R.string.shortDescriptionB4, R.string.longDescriptionB4);
        Product productB5 = new Product(R.drawable.perfium_bannner,6540000, R.drawable.b5, R.string.titleBlue, "عطر Delina پرفیوم دو مارلی",
                R.string.shortDescriptionB5, R.string.longDescriptionB5);
        productB5.setRawPrice(6700000);
        productDao.add(productB1);
        productDao.add(productB2);
        productDao.add(productB3);
        productDao.add(productB4);
        productDao.add(productB5);
        //Blue----------------------------------------------------------------------------------
        //Green---------------------------------------------------------------------------------
        Product productG1 = new Product(R.drawable.hair_banner,500000, R.drawable.g1, R.string.titleGreen, "اسپری دو فاز نرم کننده موهای رنگ شده اسکرین",
                R.string.shortDescriptionG1, R.string.longDescriptionG1);
        Product productG2 = new Product(R.drawable.hair_banner,552000, R.drawable.g2, R.string.titleGreen, "اسپري مو حجم دهنده اچ اس لاین",
                R.string.shortDescriptionG2, R.string.longDescriptionG2);
        Product productG3 = new Product(R.drawable.hair_banner,395000, R.drawable.g3, R.string.titleGreen, "اسپری حجم دهنده VOLUME UP شوارتسکف",
                R.string.shortDescriptionG3, R.string.longDescriptionG3);
        Product productG4 = new Product(R.drawable.hair_banner,667000, R.drawable.g4, R.string.titleGreen, "اسپری دوفاز Karbon اچ اس لاین",
                R.string.shortDescriptionG4, R.string.longDescriptionG4);
        Product productG5 = new Product(R.drawable.hair_banner,812000, R.drawable.g5, R.string.titleGreen, "اسپری مو توتال وان اچ اس لاین",
                R.string.shortDescriptionG5, R.string.longDescriptionG5);
        productG4.setRawPrice(710000);
        productDao.add(productG1);
        productDao.add(productG2);
        productDao.add(productG3);
        productDao.add(productG4);
        productDao.add(productG5);
        //Green---------------------------------------------------------------------------------
        //La farrerr----------------------------------------------------------------------------
        Product productL1 = new Product(R.drawable.lafarrerr,118800, R.drawable.l1, R.string.titleLa_farrerr, "تونر مولتی اکتیو پوست مختلط تا چرب لافارر",
                R.string.shortDescriptionL1, R.string.longDescriptionL1);
        Product productL2 = new Product(R.drawable.lafarrerr,149800, R.drawable.l2, R.string.titleLa_farrerr, "کرم ترمیم کننده سوکرانیکا لافارر",
                R.string.shortDescriptionL2, R.string.longDescriptionL2);
        Product productL3 = new Product(R.drawable.lafarrerr,127850, R.drawable.l3, R.string.titleLa_farrerr, "کرم مرطوب کننده پوست حساس لافارر",
                R.string.shortDescriptionL3, R.string.longDescriptionL3);
        Product productL4 = new Product(R.drawable.lafarrerr,220000, R.drawable.l4, R.string.titleLa_farrerr, "ضد آفتاب و ضد لک بی\u200Cرنگ پوست چرب و مستعد آکنه SPF50 لافارر",
                R.string.shortDescriptionL4, R.string.longDescriptionL4);
        Product productL5 = new Product(R.drawable.lafarrerr,155850, R.drawable.l5, R.string.titleLa_farrerr, "تونیک ضد ریزش مو رنگ شده و آسیب دیده لافارر",
                R.string.shortDescriptionL5, R.string.longDescriptionL5);
        productL1.setRawPrice(155000);
        productDao.add(productL1);
        productDao.add(productL2);
        productDao.add(productL3);
        productDao.add(productL4);
        productDao.add(productL5);
        //La farrerr----------------------------------------------------------------------------
        //MQ------------------------------------------------------------------------------------
        Product productMq1 = new Product(R.drawable.mq,290000, R.drawable.mq1, R.string.titleMQ, "ضد آفتاب ضد لک Bio Taches SPF50 ام کیو",
                R.string.shortDescriptionMQ1, R.string.longDescriptionMQ1);
        Product productMq2 = new Product(R.drawable.mq,269800, R.drawable.mq2, R.string.titleMQ, "ژل کرم ضدجوش ام کیو",
                R.string.shortDescriptionMQ2, R.string.longDescriptionMQ2);
        Product productMq3 = new Product(R.drawable.mq,336000, R.drawable.mq3, R.string.titleMQ, "سرم ضد لک و روشن کننده ام کیو",
                R.string.shortDescriptionMQ3, R.string.longDescriptionMQ3);
        Product productMq4 = new Product(R.drawable.mq,161000, R.drawable.mq4, R.string.titleMQ, "فوم ژل ضدجوش صورت ام کیو",
                R.string.shortDescriptionMQ4, R.string.longDescriptionMQ4);
        Product productMq5 = new Product(R.drawable.mq,92500, R.drawable.mq5, R.string.titleMQ, "کرم مرطوب کننده دست و ناخن ام کیو",
                R.string.shortDescriptionMQ5, R.string.longDescriptionMQ5);
        productDao.add(productMq1);
        productDao.add(productMq2);
        productDao.add(productMq3);
        productDao.add(productMq4);
        productDao.add(productMq5);
        //MQ------------------------------------------------------------------------------------
        //Voche---------------------------------------------------------------------------------
        Product productV1 = new Product(R.drawable.voche,88000, R.drawable.v1, R.string.titleVoche, "فوم شستشو صورت پوست های خشک و حساس وچه",
                R.string.shortDescriptionV1, R.string.longDescriptionV1);
        Product productV2 = new Product(R.drawable.voche,129000, R.drawable.v2, R.string.titleVoche, "کرم آبرسان پوست چرب وچه",
                R.string.shortDescriptionV2, R.string.longDescriptionV2);
        Product productV3 = new Product(R.drawable.voche,170000, R.drawable.v3, R.string.titleVoche, "شامپو ضدشوره مناسب موهای چرب وچه",
                R.string.shortDescriptionV3, R.string.longDescriptionV3);
        Product productV4 = new Product(R.drawable.voche,173000, R.drawable.v4, R.string.titleVoche, "لوسیون مرطوب کننده و شفاف کننده بدن وچه",
                R.string.shortDescriptionV4, R.string.longDescriptionV4);
        Product productV5 = new Product(R.drawable.voche,166500, R.drawable.v5, R.string.titleVoche, "شامپو تقویت کننده مو سیستین B6 وچه",
                R.string.shortDescriptionV5, R.string.longDescriptionV5);
        productDao.add(productV1);
        productDao.add(productV2);
        productDao.add(productV3);
        productDao.add(productV4);
        productDao.add(productV5);
        //Voche---------------------------------------------------------------------------------
        //Amutiya-------------------------------------------------------------------------------
        Product productA1 = new Product(R.drawable.amutiya,292000, R.drawable.a1, R.string.titleAmutiya, "پاک کننده دو فاز آموتیا",
                R.string.shortDescriptionA1, R.string.longDescriptionA1);
        Product productA2 = new Product(R.drawable.amutiya,286000, R.drawable.a2, R.string.titleAmutiya, "خط چشم Endless Definition آموتیا",
                R.string.shortDescriptionA2, R.string.longDescriptionA2);
        Product productA3 = new Product(R.drawable.amutiya,350000, R.drawable.a3, R.string.titleAmutiya, "ریمل Hypnotic آموتیا",
                R.string.shortDescriptionA3, R.string.longDescriptionA3);
        Product productA4 = new Product(R.drawable.amutiya,279000, R.drawable.a4, R.string.titleAmutiya, "کانتور مایع آموتیا",
                R.string.shortDescriptionA4, R.string.longDescriptionA4);
        Product productA5 = new Product(R.drawable.amutiya,169000, R.drawable.a5, R.string.titleAmutiya, "سایه چشم تکی آموتیا",
                R.string.shortDescriptionA5, R.string.longDescriptionA5);
        productDao.add(productA1);
        productDao.add(productA2);
        productDao.add(productA3);
        productDao.add(productA4);
        productDao.add(productA5);
        //Amutiya-------------------------------------------------------------------------------
    }
//    @Override
//    protected void onResume() {
//        super.onResume();
//        BasketFragment.basketAdapter.updateList(productDao.searchByQuantity(0));
//    }
}