package ir.msit87.mylocationtest.frameWork.ui.custom;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;

import java.util.Locale;

import ir.msit87.mylocationtest.frameWork.helpers.FontHelper;
import ir.msit87.mylocationtest.frameWork.helpers.UtilityHelper;


/**
 * AMEditText Class Created by Mohammad Soltanmohammadi on 7/15/2017.
 */

@SuppressLint("AppCompatCustomView")
public class AMEditText extends EditText {

    private String current = "";
    public boolean formatAsMoney = false;
    private String prefix;

    public AMEditText(Context context) {
        super(context);
        setUp(context, null);
    }

    public AMEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        setUp(context, attrs);
    }

    public AMEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setUp(context, attrs);
    }

    @TargetApi(21)
    public AMEditText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setUp(context, attrs);
    }

    private void setUp(Context context, AttributeSet attrs) {
        setFontStyle();
    }

//    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
//        super.onTextChanged(text, start, lengthBefore, lengthAfter);
//        if (!(this.prefix == null || this.prefix.length() <= 0 || text.toString().startsWith(this.prefix))) {
//            setText(this.prefix);
//        }
//        if (this.formatAsMoney && !text.toString().equals(this.current)) {
//            long value = 0;
//            try {
//                value = Long.valueOf(text.toString().replace(",", "")).longValue();
//            } catch (NumberFormatException e) {
//            }
//            this.current = String.format(Locale.US, "%,d", new Object[]{Long.valueOf(value)});
//            setText(this.current);
//            setSelection(this.current.length());
//        }
//    }
//
//    protected void onSelectionChanged(int selStart, int selEnd) {
//        super.onSelectionChanged(selStart, selEnd);
//        if (this.prefix != null && this.prefix.length() > 0) {
//            if (selStart < this.prefix.length() || selEnd < this.prefix.length()) {
//                setSelection(getText().length());
//            }
//        }
//    }

    private void setFontStyle() {
        try {
            setTypeface(FontHelper.get(FontHelper.IRAN_SANS_REGULAR, getContext()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public String text(){
//        if(this.formatAsMoney){
//            return getText().toString().replace(",","");
//        }
//        return getText().toString();
//    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
        setText(prefix);
    }

    public String getPrefix() {
        return prefix;
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        //setFontStyle();
        /**
         *
         */
        UtilityHelper.delay(1000, new UtilityHelper.DelayCallback() {
            @Override
            public void afterDelay() {
                AMEditText.this.setFontStyle();
            }
        });
    }
}
