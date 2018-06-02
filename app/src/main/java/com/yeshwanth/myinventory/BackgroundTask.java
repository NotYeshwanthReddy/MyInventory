package com.yeshwanth.myinventory;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import static android.content.ContentValues.TAG;

/*
 * Created by Yeshwanth on 22-04-2018. # _Yeshwanth_Reddy
 */
public class BackgroundTask extends AsyncTask<String, Void, String> {

    public static int N_HOD=0, N_TMP=0, N_Nescafe=0;
    public static String[] All_Items = new String[100];
    public static String[] All_Vendors = new String[100];
    public static String[] All_Costs = new String[100];
    public static String[] All_Stock = new String[100];
    public static String[] All_Item_ID = new String[100];

    public static String[] HOD_Items = new String[100];
    public static String[] HOD_Costs = new String[100];
    public static String[] HOD_Stock = new String[100];
    public static String[] HOD_Item_ID = new String[100];

    public static String[] TMP_Items = new String[100];
    public static String[] TMP_Costs = new String[100];
    public static String[] TMP_Stock = new String[100];
    public static String[] TMP_Item_ID = new String[100];

    public static String[] Nescafe_Items = new String[100];
    public static String[] Nescafe_Costs = new String[100];
    public static String[] Nescafe_Stock = new String[100];
    public static String[] Nescafe_Item_ID = new String[100];

    public static int LoginState = 0;
    AlertDialog alertDialog;
    Context ctx;
    BackgroundTask( Context ctx){
        this.ctx = ctx;
    }
    public static String UserName;

    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(ctx).create();
        alertDialog.setTitle("Login Information");
    }

    @Override
    protected String doInBackground(String... params) {


        String reg_url = "http://192.168.1.6/my_inventory/register.php";
        String login_url = "http://192.168.1.6/my_inventory/login.php";
        String item_fetch_url = "http://192.168.1.6/my_inventory/item_fetch.php";
        String item_order_url = "http://192.168.1.6/my_inventory/item_order.php";
        String method = params[0];
        if(method.equals("register")){
            String user_name = params[1];
            String email_id = params[2];
            String batch = params [3];
            String section = params [4];
            String enrollment_no = params[5];
            String phone_number = params[6];
            String password = params[7];

            try{
                URL url = new URL(reg_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                Log.d("Post done", "wwwpost");
                httpURLConnection.setDoOutput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));

                String data = URLEncoder.encode("User_Name", "UTF-8") +"="+URLEncoder.encode(user_name, "UTF-8")+"&"+
                        URLEncoder.encode("Password", "UTF-8") +"="+URLEncoder.encode(password, "UTF-8")+"&"+
                        URLEncoder.encode("Email_ID", "UTF-8") +"="+URLEncoder.encode(email_id, "UTF-8")+"&"+
                        URLEncoder.encode("Enrollment_No", "UTF-8") +"="+URLEncoder.encode(enrollment_no, "UTF-8")+"&"+
                        URLEncoder.encode("Batch", "UTF-8") +"="+URLEncoder.encode(batch, "UTF-8")+"&"+
                        URLEncoder.encode("Section", "UTF-8") +"="+URLEncoder.encode(section, "UTF-8")+"&"+
                        URLEncoder.encode("Phone", "UTF-8") +"="+URLEncoder.encode(phone_number, "UTF-8");

                Log.d("Str data", "str data");

                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();
                Log.d("os close", "os close");
                InputStream IS = httpURLConnection.getInputStream();
                IS.close();
                return "Registered Successfully...!";
            }catch (MalformedURLException e){
                e.printStackTrace();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        else if(method.equals("login")){
            String email_id = params[1];
            String password = params[2];

            try{
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                Log.d("Fetching", "wwwfetching");

                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));

                String data = URLEncoder.encode("Password", "UTF-8") +"="+URLEncoder.encode(password, "UTF-8")+"&"+
                        URLEncoder.encode("Email_ID", "UTF-8") +"="+URLEncoder.encode(email_id, "UTF-8");

                Log.d("Str data", "str data");

                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();     //Output Stream Closed
                Log.d("outputstream close", "outputstream close");

                InputStream IS = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(IS, "iso-8859-1"));
                String response = "";
                String line = "";

                while((line = bufferedReader.readLine())!= null){
                    response += line;
                }
                bufferedReader.close();
                IS.close();
                httpURLConnection.disconnect();
                return response;

            }catch (MalformedURLException e){
                e.printStackTrace();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        else if(method.equals("fetch_items")){
            StringRequest stringRequest = new StringRequest(Request.Method.GET, item_fetch_url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try{
                                JSONArray products = new JSONArray(response);
                                for(int i=0; i<products.length(); i++){
                                    JSONObject productObject = products.getJSONObject(i);

                                    All_Item_ID[i] = productObject.getString("productID");
                                    All_Items[i] = productObject.getString("name");
                                    All_Vendors[i] = productObject.getString("vendor");
                                    All_Costs[i] = Integer.toString(productObject.getInt("price"));
                                    All_Stock[i] = Integer.toString(productObject.getInt("stock"));

                                    Log.i(TAG, "updated : "+All_Vendors[i]);
                                }
                            }catch (JSONException e){
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(ctx, error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
            Volley.newRequestQueue(ctx).add(stringRequest);
            return "Items_Fetched";
        }
        else if(method.equals("order_items")){
            String enrollment_no = params[1];
            String item_ID = params[2];
            String item_quantity = params[3];

            try{
                URL url = new URL(item_order_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                Log.d("Post done", "wwwpost");
                httpURLConnection.setDoOutput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));

                String data = URLEncoder.encode("Enrollment_No", "UTF-8") +"="+URLEncoder.encode(enrollment_no, "UTF-8")+"&"+
                                URLEncoder.encode("Item_ID", "UTF-8") +"="+URLEncoder.encode(item_ID, "UTF-8")+"&"+
                                URLEncoder.encode("Item_Quantity", "UTF-8") +"="+URLEncoder.encode(item_quantity, "UTF-8");

                Log.d("Str data", "str data");

                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();
                Log.d("os close", "os close");
                InputStream IS = httpURLConnection.getInputStream();
                IS.close();
                return "Ordered Successfull...!";
            }catch (MalformedURLException e){
                e.printStackTrace();
            }catch (IOException e){
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String result) {
        if(result.equals("Registered Successfully...!")){
            Toast.makeText(ctx, result, Toast.LENGTH_SHORT).show();
        }
        else if(result.equals("Ordered Successfull...!")){
            Toast.makeText(ctx, result, Toast.LENGTH_SHORT).show();
        }
        else if(result.equals("Login Failed...")){
            alertDialog.setMessage("Login Failed");
            alertDialog.show();
        }
        else if(result.equals("Items_Fetched")){
            Toast.makeText(ctx, "Updating Items", Toast.LENGTH_SHORT).show();
            try {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        DivideItems();
                    }
                }, 1000);
            } catch (NullPointerException e){
                Log.i(TAG, "caught Null");
            }
        }
        else {
            UserName = result;
            Toast.makeText(ctx, "Welcome "+UserName, Toast.LENGTH_SHORT).show();
            LoginState = 1;
        }
    }

    public void DivideItems(){
        for (int i=0; i<100; i++){
            Log.i(TAG, "for : "+All_Vendors[i]);
            if(All_Vendors[i]==(null)){
                Log.i(TAG, "null");
                break;
            }
            else if(All_Vendors[i].equals("HOD")){
                HOD_Items[N_HOD] = All_Items[i];
                HOD_Costs[N_HOD] = All_Costs[i];
                HOD_Stock[N_HOD] = All_Stock[i];
                HOD_Item_ID[N_HOD] = All_Item_ID[i];
                Log.i(TAG, "if : N_HOD : "+N_HOD+") "+HOD_Items[N_HOD]);
                N_HOD = N_HOD+1;
            }
            else if(All_Vendors[i].equals("TMP")){
                TMP_Items[N_TMP] = All_Items[i];
                TMP_Costs[N_TMP] = All_Costs[i];
                TMP_Stock[N_TMP] = All_Stock[i];
                TMP_Item_ID[N_TMP] = All_Item_ID[i];
                Log.i(TAG, "if : N_TMP="+N_TMP+") "+TMP_Items[N_TMP]);
                N_TMP = N_TMP+1;
            }
            else if(All_Vendors[i].equals("Nescafe")){
                Nescafe_Items[N_Nescafe] = All_Items[i];
                Nescafe_Costs[N_Nescafe] = All_Costs[i];
                Nescafe_Stock[N_Nescafe] = All_Stock[i];
                Nescafe_Item_ID[N_Nescafe] = All_Item_ID[i];
                Log.i(TAG, "if : N_Nescafe="+N_Nescafe+") "+Nescafe_Items[N_Nescafe]);
                N_Nescafe = N_Nescafe+1;
            }
        }
    }
}
