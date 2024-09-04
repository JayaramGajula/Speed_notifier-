import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.*;
import android.support.annotation.Nullable;


public class data_base extends SQLiteOpenHelper {


    final static String db_name = "db_1";
    final static int version = 1;

    public data_base( Context context ) {
        super(context,db_name,null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        String query="create table t_1(name text,mobile text primary key,mobile_p text primary key)";
        DB.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop table if exists t_1");
    }

    public boolean insert_query(String name,String mobile,String mobile_p){
        SQLiteDatabase sd=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("name",name);
        contentValues.put("mobile",mobile);
        contentValues.put("mobile_p",mobile_p);
        long result=sd.insert("t_1",null,contentValues);
        if(result==-1)
        {
         return false;
        }else{
            return true;
        }
    }
}
