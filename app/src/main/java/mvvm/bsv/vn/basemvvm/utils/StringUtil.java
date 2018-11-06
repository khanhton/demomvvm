package mvvm.bsv.vn.basemvvm.utils;

import android.graphics.Paint;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by tonkhanh on 8/3/17.
 */

public class StringUtil {

    private final static Pattern LTRIM = Pattern.compile("^\\s+");

    //---------------
    // ----------------------------------------------------------------------------------------------------
    public static boolean isEmpty(EditText edt) {
        String s = edt.getText().toString();
        s = s.trim();
        return s.isEmpty();
    }
    /**
     * Check a string is whether empty or not.
     *
     * @param s The string need to check empty.
     * @return [true] if string is empty or null. Otherwise is [false].
     */
    public static boolean isEmpty(String s) {
        if (s != null) {
            s = s.trim();
        }
        return (s == null || "".equals(s));
    }

    //-------------------------------------------------------------------------------------------------------------------

    /**
     * Check a string is whether empty or not.
     *
     * @param s The string need to check empty.
     * @return [true] if string is empty or null. Otherwise is [false].
     */
    public static boolean isEmptys(String s) {
        return (s == null || "".equals(s));
    }
    //-------------------------------------------------------------------------------------------------------------------

    /**
     * Generalize integer number with specific separator and segment length.
     *
     * @param number
     * @param separator     Eg. "," or "." ...
     * @param segmentLenght
     * @return
     */
    public static String generalizeIntegerNumber(long number, String separator, int segmentLenght) {
        return generalizeIntegerNumber(String.valueOf(number), separator, segmentLenght);
    }
    //-------------------------------------------------------------------------------------------------------------------

    /**
     * Generalize string number with specific separator and segment length.
     *
     * @param numberString
     * @param separator     Eg. "," or "." ...
     * @param segmentLenght
     * @return
     */
    public static String generalizeIntegerNumber(String numberString, String separator, int segmentLenght) {
        String genString = "";
        if (numberString != null) {
            for (int i = numberString.length() - 1; i >= 0; i -= segmentLenght) {
                if (i - segmentLenght >= 0) {
                    genString = numberString.substring(i - segmentLenght + 1, i + 1) + (genString.isEmpty() ? "" : separator + genString);
                } else {
                    genString = numberString.substring(0, i + 1) + (genString.isEmpty() ? "" : separator + genString);
                }
            }
        }
        return genString;
    }

    //-------------------------------------------------------------------------------------------------------------------

    /**
     * Strike through text of TextView
     *
     * @param textView
     * @return
     */
    public static TextView strikeThroughTextView(TextView textView) {
        textView.setPaintFlags(textView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        return textView;

    }
    //-------------------------------------------------------------------------------------------------------------------

    /**
     * Remove strike through text view text flag.
     *
     * @param textView
     * @return
     */
    public static TextView removeStrikeThroughTextView(TextView textView) {
        textView.setPaintFlags(textView.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
        return textView;
    }
    //-------------------------------------------------------------------------------------------------------------------

    /**
     * Validate Request Parameter. If parameter is <code>null</code>, it be be assigned to "" (empty string).
     *
     * @param parameter
     */
    public static String validateRequestParameter(String parameter) {
        return parameter == null ? "" : parameter;
    }
    //-------------------------------------------------------------------------------------------------------------------

    public static String ltrim(String s) {
        return LTRIM.matcher(s).replaceAll("");
    }

    public static String formatFloatOneDecimal(double value) {
        DecimalFormat formater = new DecimalFormat("#.0");
        String returnValue = formater.format(value);
        if (returnValue.equals(".0"))
            returnValue = "0";
        return returnValue;
    }

    public static String formatNumberWithComma(float value) {
        DecimalFormat formatter = new DecimalFormat("#,###");
        return formatter.format(value);
    }
    
    public static String capitalizeFirstLetter(String origin) {
        return origin.length() == 0 ? origin : origin.substring(0, 1).toUpperCase() + origin.substring(1);
    }

    public static String getSpaceByLetter(String origin) {
        String returnVal = "";
        for (int i = 0; i < origin.length(); i++)
            returnVal += " ";
        return returnVal;
    }

    public static String getDoubleSpaceByNumber(int count) {
        String returnVal = "";
        for (int i = 0; i < count; i++)
            returnVal += "  ";
        return returnVal;
    }

    public static int getMaxLength(String[] strs) {
        if (strs.length > 0) {
            int maxLength = strs[0].length();
            for (int i = 0; i < strs.length; i++) {
                if (maxLength < strs[i].length())
                    maxLength = strs[i].length();
            }
            return  maxLength;
        }
        return 0;
    }

    public static String getStringFromHashSet(HashSet hashSet) {
        String returnVal = "";
        String[] strs = (String[]) hashSet.toArray(new String[hashSet.size()]);
        if (strs.length > 0) {
            for (int i = 0; i < strs.length; i++)
                returnVal += strs[i] + ",";
            try {
                returnVal = returnVal.trim().substring(0, returnVal.length() - 1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return returnVal;
    }

    public static String getStringFromListString(List<String> list) {
        String returnVal = "";
        for (int i = 0; i < list.size(); i++)
            returnVal += list.get(i) + ",";
        try {
            returnVal = returnVal.trim().substring(0, returnVal.length() - 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnVal;
    }

    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
}
