package uz.jasurbekruzimov.medicalsupport.api;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {


    //telefon raqam va parolni serverga yuborish uchun POST so'rov
    @FormUrlEncoded
    @POST("signup")
   public abstract Call<SignUpResponse> sinUp(@Field("phone") String phone, @Field("password") String password);



    //SMS kodini serverga yuborish uchun POST so'rov
    @FormUrlEncoded
    @POST("verify")
   public abstract Call<VerifyResponse> verify(@Field("phone") String phone, @Field("code") String code);



    //telefon raqam va parolni serverga yuborish uchun POST so'rov
    @FormUrlEncoded
    @POST("login")
    Call<LoginResponse> loginWithCredentials(@Field("phone") String phone, @Field("password") String password);



    //SignUpResponse va VerifyResponse klasslari serverdan keladigan javoblar uchun modellar
    public class SignUpResponse {
        private boolean success; //ro'yxatdan o'tish muvaffaqiyatli bo'lsa true qiymat qaytaradi
        private String message; //ro'yxatdan o'tish haqida xabar

        //getter va setter metodlari
        public boolean isSuccess() {
            return success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }



    public class VerifyResponse {
        private boolean success; //tasdiqlash muvaffaqiyatli bo'lsa true qiymat qaytaradi
        private String message; //tasdiqlash haqida xabar

        //getter va setter metodlari
        public boolean isSuccess() {
            return success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
