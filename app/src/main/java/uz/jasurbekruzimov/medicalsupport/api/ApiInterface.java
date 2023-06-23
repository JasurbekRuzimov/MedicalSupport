package uz.jasurbekruzimov.medicalsupport.api;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
public interface ApiInterface {
    //telefon raqam va parolni serverga yuborish uchun POST so'rov
    @FormUrlEncoded
    @POST("signup")
    Call<SignUpResponse> signUp(@Field("phone") String phone, @Field("password") String password);

    //SMS kodini serverga yuborish uchun POST so'rov
    @FormUrlEncoded
    @POST("verify")
    Call<VerifyResponse> verify(@Field("phone") String phone, @Field("code") String code);

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
