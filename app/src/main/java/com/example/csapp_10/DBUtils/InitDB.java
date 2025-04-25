package com.example.csapp_10.DBUtils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class InitDB extends SQLiteOpenHelper {

    private static final int version = 23;
    private static String databaseName = "cstore.db";
    private Context context;
    /*private static final String DB_URL = "jdbc:mysql://47.121.188.252:3306/skintradingcore";
    private static final String DB_USER = "csgo";
    private static final String DB_PASSWORD = "bZZKYJpcD47cCkkN";*/
    Object[][] data;

    public InitDB(Context context) {
        super(context, databaseName, null, version);
        this.context = context;
    }

    /* public void getDataFromRemoteDatabase() {
         new DatabaseTask().execute();
     }

     private class DatabaseTask extends AsyncTask<Void, Void, Object[][]> {

         @Override
         protected Object[][] doInBackground(Void... voids) {
             Connection connection = null;
             Statement statement = null;
             ResultSet resultSet = null;
             try {
                 // 加载 JDBC 驱动
                 Class.forName("com.mysql.cj.jdbc.Driver");
                 // 建立数据库连接
                 connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                 statement = connection.createStatement();
                 // 替换为实际的表名
                 String sql = "SELECT * FROM descriptions";
                 resultSet = statement.executeQuery(sql);

                 // 获取结果集的列数
                 int columnCount = resultSet.getMetaData().getColumnCount();
                 // 获取结果集的行数
                 resultSet.last();
                 int rowCount = resultSet.getRow();
                 resultSet.beforeFirst();

                 // 修改数组长度为 6 以匹配插入语句的参数个数
                 data = new Object[rowCount][6];
                 int rowIndex = 0;
                 while (resultSet.next()) {
                     try {
                         data[rowIndex][0] = resultSet.getString("market_name");
                         data[rowIndex][1] = resultSet.getString("type");
                         data[rowIndex][2] = 9.09;
                         data[rowIndex][3] = resultSet.getString("icon_url");
                         data[rowIndex][4] = 1;
                         // 补充 description 列的值，这里暂时设置为 null，你可以根据实际情况修改
                         data[rowIndex][5] = null;
                         rowIndex++;
                     } catch (SQLException e) {
                         Log.e("DButil", "Error getting data from ResultSet: " + e.getMessage());
                     }
                 }

                 return data;

             } catch (Exception e) {
                 Log.e("DButil", "Error accessing remote database: " + e.getMessage());
                 return null;
             } finally {
                 // 关闭资源
                 try {
                     if (resultSet != null) resultSet.close();
                     if (statement != null) statement.close();
                     if (connection != null) connection.close();
                 } catch (SQLException e) {
                     Log.e("DButil", "Error closing resources: " + e.getMessage());
                 }
             }
         }

         @Override
         protected void onPostExecute(Object[][] data) {
             if (data != null) {
                 Toast.makeText(context, "数据获取成功", Toast.LENGTH_SHORT).show();
                 // 将数据插入本地数据库
                 insertDataToLocalDB(data);
             } else {
                 Toast.makeText(context, "数据获取失败", Toast.LENGTH_SHORT).show();
             }
         }
     }

     private void insertDataToLocalDB(Object[][] data) {
         SQLiteDatabase db = getWritableDatabase();
         try {
             for (Object[] row : data) {
                 db.execSQL("insert into products(name, description, price, imageUrl, quantity, categoryId) values(?,?,?,?,?,?)", row);
             }
         } catch (Exception e) {
             Log.e("DButil", "Error inserting data to local database: " + e.getMessage());
             Toast.makeText(context, "插入数据到本地数据库失败", Toast.LENGTH_SHORT).show();
         }
     }
 */
    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            Object[][] data = {
                    {"AK-47 | 墨岩", "aaa", 60.99, "-9a81dlWLwJ2UUGcVs_nsVtzdOEdtWwKGZZLQHTxDZ7I56KU0Zwwo4NUX4oFJZEHLbXH5ApeO4YmlhxYQknCRvCo04DEVlxkKgpot7HxfDhnwMzJemkV08ykm4aOhOT9PLXQmlRZ7cRnk6eXo4nx0ADl_BBtZW-nJNeSewZrNVnVqVK4lOrnjZe-tMnBzyRku3Mj-z-DyG_ZU5Zt", 10, 6},
                    {"梦魇武器箱", "", 30.99, "-9a81dlWLwJ2UUGcVs_nsVtzdOEdtWwKGZZLQHTxDZ7I56KU0Zwwo4NUX4oFJZEHLbXU5A1PIYQNqhpOSV-fRPasw8rsUFJ5KBFZv668FFQwnfCcJmxDv9rhwIHZwqP3a-uGwz9Xv8F0j-qQrI3xiVLkrxVuZW-mJoWLMlhpWhFkc9M", 60, 3},
                    {"AK-47 | 可燃冰", "", 40.99, "-9a81dlWLwJ2UUGcVs_nsVtzdOEdtWwKGZZLQHTxDZ7I56KU0Zwwo4NUX4oFJZEHLbXH5ApeO4YmlhxYQknCRvCo04DEVlxkKgpot7HxfDhjxszJemkV09-5k5SDnvnzIITck29Y_cg_j76WrNSi3gPmrhdtNW2iJY6QcQ84M1nR81G7we_o08DquJSYzndkvD5iuygYsMTI2g", 40, 4},
                    {"截短霰弹枪 | 么么", "", 50.99, "-9a81dlWLwJ2UUGcVs_nsVtzdOEdtWwKGZZLQHTxDZ7I56KU0Zwwo4NUX4oFJZEHLbXH5ApeO4YmlhxYQknCRvCo04DEVlxkKgpopbuyLgNv1fX3cih9_92hkYSEkfHLOLLChWde_sBOh-zF_Jn4xlXs80A-Zm2nINWddwNqZQ7VrlS9xOjugZ-0v5jKznQxuCB34CuPmx2pwUYbgZ31p1Q", 20, 5},
                    {"音乐盒 | 完美世界 - 花脸", "", 60.99, "-9a81dlWLwJ2UUGcVs_nsVtzdOEdtWwKGZZLQHTxDZ7I56KU0Zwwo4NUX4oFJZEHLbXO9B9WLbU5oA9OA1zVVeKo1NvEXE94ITsH5_TxLlNlhquYJGka6omyzNGNxKeiZ-vTxGkF68F3i7DD9NTwjVfk8hZsfSmtc6vnpedC", 10, 6},
                    {"格洛克 18 型 | 圆影玉兔", "", 70.99, "-9a81dlWLwJ2UUGcVs_nsVtzdOEdtWwKGZZLQHTxDZ7I56KU0Zwwo4NUX4oFJZEHLbXH5ApeO4YmlhxYQknCRvCo04DEVlxkKgposbaqKAxf0Ob3djFN79eJmY-EmcjmMrnTn39u5cB1g_zMu4_0iwfgrUJoYTr2d4bBdFI-M1zQ_AS9ye2718W1usnIwXNjsyJx5i7D30vgvEM2zXM", 5, 7},
                    {"MAC-10 | 错觉", "", 80.99, "-9a81dlWLwJ2UUGcVs_nsVtzdOEdtWwKGZZLQHTxDZ7I56KU0Zwwo4NUX4oFJZEHLbXH5ApeO4YmlhxYQknCRvCo04DEVlxkKgpou7umeldf0Ob3fDxBvYyJh4GAnPb_JoTck29Y_cg_ibyVodut2wC2rhA5NW30I4KTcwBsMl6C_1joybi515DuuM_BmHA3vT5iuyiyR78uOQ", 3, 8},
                    {"印花 | donk（闪耀，冠军）| 2024年上海锦标赛", "", 90.99, "-9a81dlWLwJ2UUGcVs_nsVtzdOEdtWwKGZZLQHTxDZ7I56KU0Zwwo4NUX4oFJZEHLbXQ9QVcJY8gulRORE2CF7b5mNzaVGJwKgpcibmtKgpw2vzGTjpO5ciikZLFwaamar2HzmhU6pIp3r_A9tX03QDtr0tqMjqmJY6SJ1BsNQqC-1XolPCv28GW7wh3yg", 2, 9},
                    {"UMP-45 | 泥地杀手", "", 10.99, "-9a81dlWLwJ2UUGcVs_nsVtzdOEdtWwKGZZLQHTxDZ7I56KU0Zwwo4NUX4oFJZEHLbXH5ApeO4YmlhxYQknCRvCo04DEVlxkKgpoo7e1f1Jf2-r3fDJW-NCzkL-YlvnwDLbUkmJE5Yty3ruW8Iii2Afh80FoMGr7ddWUclI-Z1uG-1e2ku3pg8Lv78zKzndipGB8smSHa56L", 100, 1},
                    {"新星 | 芯轴", "", 20.99, "-9a81dlWLwJ2UUGcVs_nsVtzdOEdtWwKGZZLQHTxDZ7I56KU0Zwwo4NUX4oFJZEHLbXH5ApeO4YmlhxYQknCRvCo04DEVlxkKgpouLWzKjhoyszMZD5W_-OxhoWSqPrxN7LEmyUE6Z1137nEpN_wjgCwrkdramryJ4TEJFU6NVnT-Vjvleu7jMK4tcib1zI97aB3eEij", 80, 2},
                    {"MAG-7 | 外表生锈", "", 30.99, "-9a81dlWLwJ2UUGcVs_nsVtzdOEdtWwKGZZLQHTxDZ7I56KU0Zwwo4NUX4oFJZEHLbXH5ApeO4YmlhxYQknCRvCo04DEVlxkKgpou7uifDhhwszbZThH4OO_moaOhfn7DLbUkmJE5YtwjurErN6g2Fbl80I4MG_zd9CWdg5qNF-E-1K4x7jv1sDovsjKnCNjpGB8shajJ3fj", 60, 3},
                    {"内格夫 | 舱壁", "", 40.99, "-9a81dlWLwJ2UUGcVs_nsVtzdOEdtWwKGZZLQHTxDZ7I56KU0Zwwo4NUX4oFJZEHLbXH5ApeO4YmlhxYQknCRvCo04DEVlxkKgpouL-iLhFf2-r3dShB-M-JjYWHm_jjDLbUkmJE5Ysiie3CrI_32wLl8xZpNm_3dYCVdgE7Zw3XrwLtxr-6g5W17ZjImiFmpGB8soLen3px", 40, 4},
                    {"P250 | 设施系列·草图", "", 50.99, "-9a81dlWLwJ2UUGcVs_nsVtzdOEdtWwKGZZLQHTxDZ7I56KU0Zwwo4NUX4oFJZEHLbXH5ApeO4YmlhxYQknCRvCo04DEVlxkKgpopujwezhoyszKfShH_M6_mpS0hfLwDLfYkWNFpsci3e2ZodqgigHnrkc6Mj2mdYWVe1c3MAqD_FHtw7y-hcfuu5rKznt9-n51gd_5itg", 20, 5},
                    {"PP-野牛 | 设施系列·速写图", "", 60.99, "-9a81dlWLwJ2UUGcVs_nsVtzdOEdtWwKGZZLQHTxDZ7I56KU0Zwwo4NUX4oFJZEHLbXH5ApeO4YmlhxYQknCRvCo04DEVlxkKgpotLO_JAlf2-r3czFX6cyknY6fqOD8Oq_UqWZU7Mxkh6eX99nz31Xt-0o6YT_zJ4LDcFc5Yl6Dr1nol7jr1sS0uZiYzCFn7yF3-z-DyI5nDuVv", 10, 6},
                    {"AWP | 冥界之河", "", 70.99, "-9a81dlWLwJ2UUGcVs_nsVtzdOEdtWwKGZZLQHTxDZ7I56KU0Zwwo4NUX4oFJZEHLbXH5ApeO4YmlhxYQknCRvCo04DEVlxkKgpot621FA957P3dcjFH7c6Jh4uem_vnDKnUkmld_cBOh-zF_Jn4xg3g_BJkYzj6dYWcJAc6ZlCFrFTqwurnhMLq7c6byyc1uHV3sH6LzROpwUYbeZZSWCI", 5, 7},
                    {"AUG | 扫频仪", "", 80.99, "-9a81dlWLwJ2UUGcVs_nsVtzdOEdtWwKGZZLQHTxDZ7I56KU0Zwwo4NUX4oFJZEHLbXH5ApeO4YmlhxYQknCRvCo04DEVlxkKgpot6-iFBRw7P7NYjV9_92wkZSSlfv1MLDum25V4dB8xO-T8N7wjFDlrhA4MmGhJtWScwVrY1CD_QK5xOy8g8K_uZnMyHVk73E8pSGKrNOKfW8", 3, 8},
                    {"PP-野牛 | 红苹果", "", 90.99, "-9a81dlWLwJ2UUGcVs_nsVtzdOEdtWwKGZZLQHTxDZ7I56KU0Zwwo4NUX4oFJZEHLbXH5ApeO4YmlhxYQknCRvCo04DEVlxkKgpotLO_JAlfwPz3YzhG09C_k4if2a73Me2CxW5Sup1wj7HEptun31G280U6MWHydobBJA8-ZlnQ_QPryb_xxcjrlO1CYf0", 2, 9},
                    {"MP7 | 渐变之色", "", 100.99, "-9a81dlWLwJ2UUGcVs_nsVtzdOEdtWwKGZZLQHTxDZ7I56KU0Zwwo4NUX4oFJZEHLbXH5ApeO4YmlhxYQknCRvCo04DEVlxkKgpou6ryFAZh7PXJdTh94czhq4yCkP_gfe-ExDNVsJ1y2rDC99_zi1LjqUVtYGD1JICdJwdtYFHZ-le5kuzrhJCi_MOeD-m-BeA", 1, 10},
                    {"R8 左轮手枪 | 氮化处理", "", 30.99, "-9a81dlWLwJ2UUGcVs_nsVtzdOEdtWwKGZZLQHTxDZ7I56KU0Zwwo4NUX4oFJZEHLbXH5ApeO4YmlhxYQknCRvCo04DEVlxkKgpopL-zJAt21uH3YjJ94863moeOqPb3ML7fgngC18h0juDU-MKl2QOwrxY_Nm31JdWce1RqZ1zU-FS8kOu6h8O_vJXMzHtksnIq4nmLgVXp1mt-q3CA", 60, 3},
                    {"M4A4 | 主机", "", 40.99, "-9a81dlWLwJ2UUGcVs_nsVtzdOEdtWwKGZZLQHTxDZ7I56KU0Zwwo4NUX4oFJZEHLbXH5ApeO4YmlhxYQknCRvCo04DEVlxkKgpou-6kejhh3szLeC9B-dWilo-KhfPLILLdgG5D18h0juDU-MKgjQHm8xI6az_7dtPAcgE_M1zS81K9lLzv08W1vJjKmyNiuSZ3sCrbgVXp1oAuxOQN", 40, 4},
                    {"加利尔 AR | 冰核聚变", "", 50.99, "-9a81dlWLwJ2UUGcVs_nsVtzdOEdtWwKGZZLQHTxDZ7I56KU0Zwwo4NUX4oFJZEHLbXH5ApeO4YmlhxYQknCRvCo04DEVlxkKgposbupIgthwczAaAJM-d-6kYGZqOT_JrfdhVRQ-dBwteTE8YXghRqy8hc9Y2undoHEdQQ4YV6FqQfoxu2-15G_6cjBm3oyvCFw7S7YzkTjn1gSOYu9AFib", 20, 5},
                    {"PP-野牛 | 沙漠虚线", "", 60.99, "-9a81dlWLwJ2UUGcVs_nsVtzdOEdtWwKGZZLQHTxDZ7I56KU0Zwwo4NUX4oFJZEHLbXH5ApeO4YmlhxYQknCRvCo04DEVlxkKgpotLO_JAlfwOP3ZTxS6eOlnI-Zg8jnMrXVqWZU7Mxkh6fA89T30VexrhZuZGGhdtLHcwNrYwqE_Vftwuzt0JS4u87AnCBksici-z-DyLStQtcq", 10, 6},
                    {"UMP-45 | 都市 DDPAT", "", 70.99, "-9a81dlWLwJ2UUGcVs_nsVtzdOEdtWwKGZZLQHTxDZ7I56KU0Zwwo4NUX4oFJZEHLbXH5ApeO4YmlhxYQknCRvCo04DEVlxkKgpoo7e1f1Jf2-r3dTlS7ciJgZKJqPrxN7LEmyVXu8YpjrzAoNWgiwC1rRA9YGCnddCUIwU-MlnR_VbrxO-81pbtvpvO1zI97ctlpOGN", 5, 7},
                    {"G3SG1 | 丛林虚线", "", 80.99, "-9a81dlWLwJ2UUGcVs_nsVtzdOEdtWwKGZZLQHTxDZ7I56KU0Zwwo4NUX4oFJZEHLbXH5ApeO4YmlhxYQknCRvCo04DEVlxkKgposem2LFZfwOP3ZTxS6eOlnI-Zg8j-JrXWmm5u5cB1g_zMu4-s2AGy8xJoamr0J9fEcwU4Y1HUrFm2k-ju1sDv7ZWczHZj7iVw4ynD30vg5sRy09E", 3, 8},
                    {"新星 | 捕食者", "", 90.99, "-9a81dlWLwJ2UUGcVs_nsVtzdOEdtWwKGZZLQHTxDZ7I56KU0Zwwo4NUX4oFJZEHLbXH5ApeO4YmlhxYQknCRvCo04DEVlxkKgpouLWzKjhzw8zSdD9Q7d-3mb-GkvP9JraflDhXvMdw27jFp4_32FLl-UpkNmj0LNKWcQI4NA3Wq1TvkL_p15K0uIOJlyUBLttwMQ", 2, 9},
                    {"FN57 | 暮色森林", "", 100.99, "-9a81dlWLwJ2UUGcVs_nsVtzdOEdtWwKGZZLQHTxDZ7I56KU0Zwwo4NUX4oFJZEHLbXH5ApeO4YmlhxYQknCRvCo04DEVlxkKgposLOzLhRlxfbGTjVb09q5hoWYg8j6OrzZglRc7cF4n-SP9NmtiwG1_Es9NjigIYaSegE7aAzSrATtw7vvhcPu75-dzXRnvXMl7GGdwUJLDBM-tQ", 1, 10},
                    {"双持贝瑞塔 | 雇佣兵", "", 60.99, "-9a81dlWLwJ2UUGcVs_nsVtzdOEdtWwKGZZLQHTxDZ7I56KU0Zwwo4NUX4oFJZEHLbXH5ApeO4YmlhxYQknCRvCo04DEVlxkKgpos7asPwJfwPz3YTBB09GzkImemrmla-3SxjhTscQhi7jDrYqn31Dh-0BqZm73JdLDc1Q_Y13Q-Fi9yefqm9bi68eOluDW", 10, 6},
                    {"SSG 08 | 蓝色云杉", "", 70.99, "-9a81dlWLwJ2UUGcVs_nsVtzdOEdtWwKGZZLQHTxDZ7I56KU0Zwwo4NUX4oFJZEHLbXH5ApeO4YmlhxYQknCRvCo04DEVlxkKgpopamie19fwPz3fDJR_-O-kYGdjrn1YOqIwm0Du5x13rmV89ysjAbl-BE-Yj3wcdPEIw4_YQzX_lG8kr_qm9bi69aI4j5-", 5, 7},
                    {"Tec-9 | 陆军网格", "", 80.99, "-9a81dlWLwJ2UUGcVs_nsVtzdOEdtWwKGZZLQHTxDZ7I56KU0Zwwo4NUX4oFJZEHLbXH5ApeO4YmlhxYQknCRvCo04DEVlxkKgpoor-mcjhzw8zFdC5K092kmZm0mvLwOq7c2D4Evp1y3bmQ89miilDt_hU-YGjwIIGccAM6YArZ81jolejs0JfovszXiSw0VV39XcQ", 3, 8},
                    {"AUG | 暴风呼啸", "", 90.99, "-9a81dlWLwJ2UUGcVs_nsVtzdOEdtWwKGZZLQHTxDZ7I56KU0Zwwo4NUX4oFJZEHLbXH5ApeO4YmlhxYQknCRvCo04DEVlxkKgpot6-iFBRv7ODcfi9P6s65mpS0mvLwOq7c2G4D7JJ03L2U99isi1HsrRc-MmDydYPEJw82aVHZqFm4k-jq0MTuvZ_XiSw0rb4jchA", 2, 9},
                    {"AK-47 | 迷踪秘境", "", 100.99, "-9a81dlWLwJ2UUGcVs_nsVtzdOEdtWwKGZZLQHTxDZ7I56KU0Zwwo4NUX4oFJZEHLbXH5ApeO4YmlhxYQknCRvCo04DEVlxkKgpot7HxfDhjxszJemkV092sgIWIqPrxN7LEmyUCvpIgjryRrdyl3QTi_kplZ2_yLdORIwVtNFuD-gfvxb-615a06ZrB1zI97RB85qIO", 1, 10},
                    {"M4A1 消音型 | 氮化处理", "", 60.99, "-9a81dlWLwJ2UUGcVs_nsVtzdOEdtWwKGZZLQHTxDZ7I56KU0Zwwo4NUX4oFJZEHLbXH5ApeO4YmlhxYQknCRvCo04DEVlxkKgpou-6kejhz2v_Nfz5H_uOlm7-Ehfb6NL7ul2hS7ctlmdbN_Iv9nBrmqUQ-Y2H1doGTdg5qZw2GrFm3wLjp0JPovp3LmiRjsiYgsXyOy0G0n1gSOY9ZIQF5", 10, 6},
                    {"音乐盒 | The Verkkars - EZ4ENCE", "", 70.99, "-9a81dlWLwJ2UUGcVs_nsVtzdOEdtWwKGZZLQHTxDZ7I56KU0Zwwo4NUX4oFJZEHLbXO9B9WLbU5oA9OA1jYQvKoxcTYUk9nGlQG-LnyfV42iveQcmwW7YW3w9SIwKbyY7-Ek21S7ZMl37rHoo6i2Fay_0ByIzekS-mLnh0", 5, 7},
                    {"印花 | Tyloo | 2019年柏林锦标赛", "", 80.99, "-9a81dlWLwJ2UUGcVs_nsVtzdOEdtWwKGZZLQHTxDZ7I56KU0Zwwo4NUX4oFJZEHLbXQ9QVcJY8gulRfSV7cTur_h56KHEltKUoCs-2jKFUyg6ebI2gWvoriw9aIlqGgYu-AwToEupMg072Q9tugjAex5QMyNBjT9VQ3", 3, 8},
                    {"AK-47 | 红线", "", 90.99, "-9a81dlWLwJ2UUGcVs_nsVtzdOEdtWwKGZZLQHTxDZ7I56KU0Zwwo4NUX4oFJZEHLbXH5ApeO4YmlhxYQknCRvCo04DEVlxkKgpot7HxfDhjxszJemkV09-5lpKKqPrxN7LEmyVS7cYg3LuT94qm21GyqUpsa2j7IIDDJwI7YwvRrFi7lOa5hpfpvs_A1zI97fpmYHCU", 2, 9},
                    {"AWP | 二西莫夫", "", 100.99, "-9a81dlWLwJ2UUGcVs_nsVtzdOEdtWwKGZZLQHTxDZ7I56KU0Zwwo4NUX4oFJZEHLbXH5ApeO4YmlhxYQknCRvCo04DEVlxkKgpot621FAR17PLfYQJD_9W7m5a0n_L1JaKfk21XvZFyjLrC8Nms2gK1_EY5MW3zcI-SI1BqYguE_1a4kOu5hMS87oOJlyV2PoDpPA", 1, 10},
                    {"执行者 | 凤凰战士", "", 60.99, "-9a81dlWLwJ2UUGcVs_nsVtzdOEdtWwKGZZLQHTxDZ7I56KU0Zwwo4NUX4oFJZEHLbXA6Q1NL4kmrAlOA0_FVPCi2t_fUkRxNztDu4W1Iwhl3frQTitD_tW3mpSN2fOsMr-Dwj8Aup1307-W9N-s2wCw_BFpYDyiJdfGIFI5NA3R-VK9lO7xxcjrKxt2X-E", 10, 6},
                    {"布章 | 英勇", "", 70.99, "-9a81dlWLwJ2UUGcVs_nsVtzdOEdtWwKGZZLQHTxDZ7I56KU0Zwwo4NUX4oFJZEHLbXT4BhcJo8h5hhcX0mAFqu91tvQW2J2NwVBufTycgZm0qqYImsbvIi0xNeNwKDxZenSwz0C6ZEn0rnE94_23VC3_EppfSmtc27fmEmr", 5, 7},
                    {"AWP | 毛细血管", "", 80.99, "-9a81dlWLwJ2UUGcVs_nsVtzdOEdtWwKGZZLQHTxDZ7I56KU0Zwwo4NUX4oFJZEHLbXH5ApeO4YmlhxYQknCRvCo04DEVlxkKgpot621FAR17PLfYQJU6dW4q42Ok_7hPvXVxTpQvJd1jrGZpY-sjQfk_UZraz2gd9Wdd1RtNVGFq1i5yOjtjcO-ot2XnlArR5qD", 3, 8},
                    {"SCAR-20 | 执行者", "", 90.99, "-9a81dlWLwJ2UUGcVs_nsVtzdOEdtWwKGZZLQHTxDZ7I56KU0Zwwo4NUX4oFJZEHLbXH5ApeO4YmlhxYQknCRvCo04DEVlxkKgpopbmkOVUw7PTbTi5B7c7kxL-OmfH7IbjUhFRd4cJ5nqfF9Inw3wO1-RA6Nzz6I4LDdVRsMw7V-AW3lefq0J7t7svPm3BiviFx-z-DyJBF3MU6", 2, 9},
                    {"运动手套（★） | 夜行衣", "", 100.99, "-9a81dlWLwJ2UUGcVs_nsVtzdOEdtWwKGZZLQHTxDZ7I56KU0Zwwo4NUX4oFJZEHLbXH5ApeO4YmlhxYQknCRvCo04DAQ1JmMR1osbaqPQJz7ODYfi9W9eO8lYeeluXLPr7Vn35cpp0i0-jDo9r20VbmrUo4Yj2gJYKUcAE_Z1CF8lS7kr_thcS46cjNwCR9-n51-wVTfcs", 1, 10},
                    {"爪子刀（★） | 多普勒", "", 31.1, "-9a81dlWLwJ2UUGcVs_nsVtzdOEdtWwKGZZLQHTxDZ7I56KU0Zwwo4NUX4oFJZEHLbXH5ApeO4YmlhxYQknCRvCo04DEVlxkKgpovbSsLQJf2PLacDBA5ciJlY20k_jkI7fUhFRB4MRij73--YXygED6rkRuZGDxLYCddlc3MFzSrlDslOfr1J_uup7MzHUxviUjtimPmxWyhAYMMLLRA6IwHA", 10, 1},
                    {"P250 | 元素轮廓", "", 91, "-9a81dlWLwJ2UUGcVs_nsVtzdOEdtWwKGZZLQHTxDZ7I56KU0Zwwo4NUX4oFJZEHLbXH5ApeO4YmlhxYQknCRvCo04DEVlxkKgpopujwezhhwszYI2gS09-5mpSEguXLP7LWnn8fuZEg2LnF84qs0QWw-kdvZ2H6cYGSIAVrZljTrFjolLrphJe_6pvNnWwj5HenfXtLAw", 1, 8},
                    {"沙漠之鹰（StatTrak™） | 青铜装饰", "", 2.1, "-9a81dlWLwJ2UUGcVs_nsVtzdOEdtWwKGZZLQHTxDZ7I56KU0Zwwo4NUX4oFJZEHLbXH5ApeO4YmlhxYQknCRvCo04DEVlxkKgposr-kLAtl7PLFTj9Q49Kskb-Yh_bmOLfUqWZU7Mxkh6eSpIj0iVDk_hE6NW-ncIWXewBsNF2C8wLvl-brh8K96sjMznBhuick-z-DyFacSMYV", 34, 9},
                    {"M4A4 | 狮鹫", "", 132.3, "-9a81dlWLwJ2UUGcVs_nsVtzdOEdtWwKGZZLQHTxDZ7I56KU0Zwwo4NUX4oFJZEHLbXH5ApeO4YmlhxYQknCRvCo04DEVlxkKgpou-6kejhjxszFJTwW09uknYaNnvnLP7LWnn8f7Zwo3-iRpI-t3QLlqkc9Nm31JoPHIwFoYFDX8gS4yL3uhZ7pvcnImGwj5HcFfKvMuQ", 65, 2},
                    {"格洛克 18 型 | 粉碎者", "", 23.1, "-9a81dlWLwJ2UUGcVs_nsVtzdOEdtWwKGZZLQHTxDZ7I56KU0Zwwo4NUX4oFJZEHLbXH5ApeO4YmlhxYQknCRvCo04DEVlxkKgposbaqKAxf0uL3djFN79eJl4-Cm_LwDLfYkWNFppMhiOqT8Yqiigfn_RdtNjr1J4aQewQ5aVnVqQe3k-_v1Ja67s6fz3t9-n51ouqtKMA", 34, 1},
                    {"印花 | apEX（全息）| 2024年上海锦标赛", "", 12.1, "-9a81dlWLwJ2UUGcVs_nsVtzdOEdtWwKGZZLQHTxDZ7I56KU0Zwwo4NUX4oFJZEHLbXQ9QVcJY8gulRORE2CF7b5mNzaVGJ1NQFPibKqJwguh_WZIjlDv4_ikoaJwqWgZb7UxjwFuMZ13uyZrYqhiVWyqhJuZmn6cZjVLFE88a2jXg", 56, 34},
                    {"印花 | electronic | 2024年上海锦标赛", "", 18, "-9a81dlWLwJ2UUGcVs_nsVtzdOEdtWwKGZZLQHTxDZ7I56KU0Zwwo4NUX4oFJZEHLbXQ9QVcJY8gulRORE2CF7b5mNzaVGJxKQFUoqiqJQ5jnaOZKG9DtNi0ldbYx_WjMOKFl25Quscj0r-X94nzjlLjqBVvajj3IoCLMlhp1laYMvo", 34, 2},
                    {"P250（纪念品） | 天旱", "", 12.1, "-9a81dlWLwJ2UUGcVs_nsVtzdOEdtWwKGZZLQHTxDZ7I56KU0Zwwo4NUX4oFJZEHLbXH5ApeO4YmlhxYQknCRvCo04DEVlxkKgpopujwezhzw8zMdC5H_siJh4uem_vnDL_QgWVu5Mx2gv2P89Tz3Fe38kFpZ2zyJ9Wcdg47ZFyB-Fe4xujogp-175jLm3Bg73J27GGdwUJU6q_GRw", 34, 1},
                    {"双持贝瑞塔 | 藏身处", "", 12.1, "-9a81dlWLwJ2UUGcVs_nsVtzdOEdtWwKGZZLQHTxDZ7I56KU0Zwwo4NUX4oFJZEHLbXH5ApeO4YmlhxYQknCRvCo04DEVlxkKgpos7asPwJf1-bJfQJA6c6zgJSKhMj4Nq_Zl2du79d4hNbM8Ij8nVn6qkVqYW2hLIKSIwc9YlHYrwO5we7v0cW475XBynFjuyYj7SrfmxG3gQYMMLKbmJ9MzA", 34, 1},
                    {"印花 | 来一根", "", 31.1, "-9a81dlWLwJ2UUGcVs_nsVtzdOEdtWwKGZZLQHTxDZ7I56KU0Zwwo4NUX4oFJZEHLbXQ9QVcJY8gulReQ0HdUuqkw9acUFJ5KBFZv668FFUwgaeHYTxS6c6JlpWGqPbLILbenW4fv5Am0rzH9o6i0FDn-BE5am70J4KQIANtaVCEqFHtybzv1J677ZnPyWwj5HfacQqUxA", 10, 1},
                    {"音乐盒 | Knock2 - 燥起来！", "", 91, "-9a81dlWLwJ2UUGcVs_nsVtzdOEdtWwKGZZLQHTxDZ7I56KU0Zwwo4NUX4oFJZEHLbXO9B9WLbU5oA9OA0feSOemhfCDARMgd1IC5Lj3fFc4hvaRKWxBuYzhxNHTk_WgMb-JwmkJvsYp3L-R99qsxkS6rHmEXbG3", 1, 8},
                    {"千瓦武器箱", "", 2.1, "-9a81dlWLwJ2UUGcVs_nsVtzdOEdtWwKGZZLQHTxDZ7I56KU0Zwwo4NUX4oFJZEHLbXU5A1PIYQNqhpOSV-fRPasw8rsUFJ5KBFZv668FFQznaKdID5D6d23ldHSwKOmZeyEz21XvZZ12LzE9t6nigbgqkplNjihJIaLMlhpF1ZeR5c", 34, 9},
                    {"印花 | G2 Esports | 2023年巴黎锦标赛", "", 132.3, "-9a81dlWLwJ2UUGcVs_nsVtzdOEdtWwKGZZLQHTxDZ7I56KU0Zwwo4NUX4oFJZEHLbXQ9QVcJY8gulRNTV7ZVLb9hZycVA86c1RT4Oz8LQE0iqaRc2QSvNrgxdXZkaKkYrjTlTwGucAnj7yUpI_02RqkpRRiWH_I3g", 65, 2},
                    {"MP5-SD | 液化", "", 23.1, "-9a81dlWLwJ2UUGcVs_nsVtzdOEdtWwKGZZLQHTxDZ7I56KU0Zwwo4NUX4oFJZEHLbXH5ApeO4YmlhxYQknCRvCo04DEVlxkKgpou6rwOANf0Ob3fC0X_9iJhZWClPzLP7LAg2JV6dF4hef-_Yn0nk36_kpvYW32IoSXJ1I5Mw7YqFXoxrvvhMS7vs7Am3s1uXZ0s3val0figgYMMLJ_TWF1bw", 34, 1},
                    {"M4A1 消音型 | 隐伏帝王龙", "", 12.1, "-9a81dlWLwJ2UUGcVs_nsVtzdOEdtWwKGZZLQHTxDZ7I56KU0Zwwo4NUX4oFJZEHLbXH5ApeO4YmlhxYQknCRvCo04DEVlxkKgpou-6kejhz2v_Nfz5H_uO1gb-Gw_alIITXk25V4ct2te_T8ILvkWu5rhc1JjTtIobBcwA-ZV-G_lHvwuzr15fq75WamHRm6Scj4XiJmB3hhB8ea-FnhOveFwthv4bIjg", 56, 34},
                    {"挂件 | 宝宝AK", "", 45.6, "-9a81dlWLwJ2UUGcVs_nsVtzdOEdtWwKGZZLQHTxDZ7I56KU0Zwwo4NUX4oFJZEHLbXI5BVcJos7pwgSW0nRV-uj6J6cWF5LMhRZibuuFAVhwPaGImwR6tiwwNDcz_akYr_VlzsAscF12uiTodij2w2x80Y4Y2vxdoDAdhh-Pw_srEH5mQ", 23, 5},
                    {"印花 | 卡哇伊眼睛（闪耀）", "", 78.9, "-9a81dlWLwJ2UUGcVs_nsVtzdOEdtWwKGZZLQHTxDZ7I56KU0Zwwo4NUX4oFJZEHLbXQ9QVcJY8gulReQ0HdUuqkw9acQEl9Jg9SpIWmOQZmx7zPfTRW-Nmkq4uKgPb9OoTUj25Cppwo2buX9Nus3lLj_ktla2n2J4CXJAZoMl2B_Fftl-i-gsXotJ6fzHB9-n51ZhKrj10", 89, 12},
                    {"印花 | 小丑鼻子", "", 5.2, "-9a81dlWLwJ2UUGcVs_nsVtzdOEdtWwKGZZLQHTxDZ7I56KU0Zwwo4NUX4oFJZEHLbXQ9QVcJY8gulReQ0HdUuqkw9acQEl9Jg9SpIWmOQZmx7zYcC1H_uO1mI-cmcj6PKjU2D8F7MFw2uqWotin0FK1_UJsYG-iJ9WWIQNoaVrQ8lnol-fvg5a1tZTXiSw0X5yKY2k", 45, 6},
                    {"印花 | 蝴蝶结", "", 110.7, "-9a81dlWLwJ2UUGcVs_nsVtzdOEdtWwKGZZLQHTxDZ7I56KU0Zwwo4NUX4oFJZEHLbXQ9QVcJY8gulReQ0HdUuqkw9acQEl9Jg9SpIWmOQZmx7zYcC1H_uOknYKJmPm6MO-Gzm5Xvpwli7CXpYj0jAzs_UE_N2mnLIOXdVM6MAqB-wS5w7u60Yj84srM5IYGPw", 78, 3},
                    {"AK-47 | 交叉渐变", "", 18.3, "-9a81dlWLwJ2UUGcVs_nsVtzdOEdtWwKGZZLQHTxDZ7I56KU0Zwwo4NUX4oFJZEHLbXH5ApeO4YmlhxYQknCRvCo04DEVlxkKgpot7HxfDhjxszPYzxS5NW1q4-dkuX4MqLul2AFv_p9g-7J4cKk3gOy-kI4YGD0LdXHdw47Y1_Vrle6wrzuhsS96J_MyyAxvHEmtHnYgVXp1hyNAMKW", 21, 9},
                    {"PP-野牛（纪念品） | 安乐蜥", "", 27.4, "-9a81dlWLwJ2UUGcVs_nsVtzdOEdtWwKGZZLQHTxDZ7I56KU0Zwwo4NUX4oFJZEHLbXH5ApeO4YmlhxYQknCRvCo04DEVlxkKgpotLO_JAlf2-r3fTRY7c6yq5OAnvnLP7LWnn8f7ZYm3--Uo9-s2w3t-0FoMWHyLIGXdgJvZQrQ_1btlL3o18S4vs_Lzmwj5HftjkUzng", 36, 15},
                    {"印花 | w0nderful | 2024年上海锦标赛", "", 8.5, "-9a81dlWLwJ2UUGcVs_nsVtzdOEdtWwKGZZLQHTxDZ7I56KU0Zwwo4NUX4oFJZEHLbXQ9QVcJY8gulRORE2CF7b5mNzaVGJjdQpTs6ijPgsu0fHNd2kb7o-wkdDZkfP3ar6BwWpT7pR33u3D996hjA3k-hZvY2r2JpjVLFE-qFJ8nQ", 12, 7},
                    {"上海 2024 金色硬币", "", 145.2, "-9a81dlWLwJ2UUGcVs_nsVtzdOEdtWwKGZZLQHTxDZ7I56KU0Zwwo4NUX4oFJZEHLbXQ9Q1LO5kNoBhSQl-fV_OSx8bQWFh5GlYH5O6aLAhs173JJjgStNi3ldLTwKWjN-vVkjhV7MZ33b-Z9o-n31fk_kVpZT_3ctPGbEZgNviz8R8k", 92, 4},
                    {"加利尔 AR | Grey Smoke", "", 33.6, "-9a81dlWLwJ2UUGcVs_nsVtzdOEdtWwKGZZLQHTxDZ7I56KU0Zwwo4NUX4oFJZEHLbXH5ApeO4YmlhxYQknCRvCo04DEVlxkKgposbupIgthwczbfj5K0966m5KJqPXjDLzQmmJd6ddOh-zF_Jn4xgHm-0NpZDvyI4OQcA5oY1uD_AXvxLvthsO5uZnIzHE36CIk4y7eykCpwUYbgcFQ8Ls", 47, 21},
                    {"印花 | Team Spirit（闪耀）| 2024年上海锦标赛", "", 62.8, "-9a81dlWLwJ2UUGcVs_nsVtzdOEdtWwKGZZLQHTxDZ7I56KU0Zwwo4NUX4oFJZEHLbXQ9QVcJY8gulRORE2CF7b5mNzDWk9LIgheoq6gOUk00veYKWwS7Yy0w9iPwqOjZO_XxWkFv8Qo3r-Xo96j2Vfmr0NlYmmlOsbLJQ99WSX5", 53, 18},
                    {"沙漠之鹰 | 热处理", "", 1.9, "-9a81dlWLwJ2UUGcVs_nsVtzdOEdtWwKGZZLQHTxDZ7I56KU0Zwwo4NUX4oFJZEHLbXH5ApeO4YmlhxYQknCRvCo04DEVlxkKgposr-kLAtl7PLZTjlH7du6kb-IluTxDLPQhG9U5sB1tbv--InxgUG55URpYT-iI4eSegA9N13Y81DvlLjvhpDpvJqYzHMxvXJ25CrYnBO_gk5SLrs4W42nI18", 18, 2},
                    {"2024 年服役勋章", "", 167.4, "-9a81dlWLwJ2UUGcVs_nsVtzdOEdtWwKGZZLQHTxDZ7I56KU0Zwwo4NUX4oFJZEHLbXQ9Q1LO5kNoBhSQl-fVOG_wcbQVmJ5IABWuoX3e1U07P_efWwM7Y2wxtPSxqDwZerQzzkCucB337vEoI6j0VLg-hdpZ2n7d4TBIwNrZUaQpAZjby2rZQ", 101, 6}
            };
            db.execSQL("drop table if exists users");
            db.execSQL("create table users(phone varchar(20), " +
                    "pasword varchar(64), " +
                    "steamid varchar(128))");


            //创建order表
            db.execSQL("drop table if exists orders");
            db.execSQL("create table orders(id integer primary key autoincrement, " +
                    "userId integer, " +
                    "productName varchar(32)," +
                    "imageUrl varchar(256), " +
                    "productPrice double, " +
                    "orderDate varchar(20), " +
                    "status boolean)");

            db.execSQL("drop table if exists products");
            db.execSQL("create table products(id integer primary key autoincrement, " +
                    "name varchar(64), " +
                    "description varchar(128), " +
                    "price double, " +
                    "imageUrl varchar(255), " +
                    "quantity integer, " +
                    "categoryId integer)");
            try {
                for (Object[] row : data) {
                    db.execSQL("insert into products(name, description, price, imageUrl, quantity, categoryId) values(?,?,?,?,?,?)", row);
                }
            } catch (Exception e) {
                Toast.makeText(context, "InitData" + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(context, "InitData " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.beginTransaction();
        db.execSQL("drop table if exists users");
        db.execSQL("drop table if exists products");
        onCreate(db);
        db.setTransactionSuccessful();
        db.endTransaction();
    }


}