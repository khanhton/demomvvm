package mvvm.bsv.vn.basemvvm.utils;

/**
 * Created by Khanh Ton on 2019-05-28.
 */
public class Utils {
   public static  int doAction(int a, int b, ExecuteFunction func){
       return func.execute(a,b);

   }
    @FunctionalInterface
    public interface ExecuteFunction {
        public int execute(int a, int b);
    }

}