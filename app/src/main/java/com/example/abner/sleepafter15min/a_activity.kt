////package com.example.a29844.a
//
//import java.io.FileWriter
//import android.content.Intent
//import android.support.v4.content.ContextCompat.startActivity
//import java.nio.file.Files.exists
//
//
//
//
////保存为txt格式
//public static void stringTxt(String str)
//    {
//        try {
//            FileWriter fw = new FileWriter("   " + "/cmd.txt");//l路径待写入
//            fw.flush();
//            fw.write(str);
//            fw.close();
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//}
//
//
//
//
//fun shareMsg(activityTitle: String, msgTitle: String, msgText: String,
//             imgPath: String?) {
//    val intent = Intent(Intent.ACTION_SEND)
//    if (imgPath == null || imgPath == "") {
//        intent.type = "text/plain" // 纯文本//支持分享（可输出）
//        /**
//         * 分享功能
//         *
//         * @param context
//         * 上下文
//         * @param activityTitle
//         * Activity的名字
//         * @param msgTitle
//         * 消息标题
//         * @param msgText
//         * 消息内容
//         * @param imgPath
//         * 图片路径，不分享图片则传null
//         */
//    } else {
//        val f = File(imgPath)
//        if (f != null && f.exists() && f.isFile()) {
//            intent.type = "image/jpg"
//            val u = Uri.fromFile(f)
//            intent.putExtra(Intent.EXTRA_STREAM, u)
//        }
//    }
//    intent.putExtra(Intent.EXTRA_SUBJECT, msgTitle)
//    intent.putExtra(Intent.EXTRA_TEXT, msgText)
//    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
//    startActivity(Intent.createChooser(intent, activityTitle))
//}
//
//
////文字排版
//public class CYTextView extends TextView {
//    public  static  int m_iTextHeight; //文本的高度
//    public  static  int m_iTextWidth;//文本的宽度
//
//    private Paint mPaint = null;
//    private String string="";
//    private float LineSpace = 0;//行间距
//
//    public CYTextView(Context context, AttributeSet set)
//    {
//        super(context,set);
//
//        TypedArray typedArray = context.obtainStyledAttributes(set, R.styleable.CYTextView);
//
//        int width = typedArray.getInt(R.styleable. CY TextView_textwidth, 320);
//        float textsize = typedArray.getDimension(R.styleable. CY TextView_textSize, 24);
//        int textcolor = typedArray.getColor(R.styleable. CY TextView_textColor, -1442840576);
//        float linespace = typedArray.getDimension(R.styleable. CY TextView_lineSpacingExtra, 15);
//        int typeface = typedArray.getColor(R.styleable. CY TextView_typeface, 0);
//
//        typedArray.recycle();
//
//        //设置 CY TextView的宽度和行间距www.linuxidc.com
//        m_iTextWidth=width;
//        LineSpace=linespace;
//
//        // 构建paint对象
//        mPaint = new Paint();
//        mPaint.setAntiAlias(true);
//        mPaint.setColor(textcolor);
//        mPaint.setTextSize(textsize);
//        switch(typeface){
//            case 0:
//            mPaint.setTypeface(Typeface.DEFAULT);
//            break;
//            case 1:
//            mPaint.setTypeface(Typeface.SANS_SERIF);
//            break;
//            case 2:
//            mPaint.setTypeface(Typeface.SERIF);
//            break;
//            case 3:
//            mPaint.setTypeface(Typeface.MONOSPACE);
//            break;
//            default:
//            mPaint.setTypeface(Typeface.DEFAULT);
//            break;
//        }
//
//    }
//
//    @Override
//    protected void onDraw(Canvas canvas)
//    {
//        super.onDraw(canvas);
//
//        char ch;
//        int w = 0;
//        int istart = 0;
//        int m_iFontHeight;
//        int m_iRealLine=0;
//        int x=2;
//        int y=30;
//
//        Vector    m_String=new Vector();
//
//        FontMetrics fm = mPaint.getFontMetrics();
//        m_iFontHeight = (int) Math.ceil(fm.descent - fm.top) + (int)LineSpace;//计算字体高度（字体高度＋行间距）
//
//        for (int i = 0; i < string.length(); i++)
//        {
//            ch = string.charAt(i);
//            float[] widths = new float[1];
//            String srt = String.valueOf(ch);
//            mPaint.getTextWidths(srt, widths);
//
//            if (ch == '/n'){
//                m_iRealLine++;
//                m_String.addElement(string.substring(istart, i));
//                istart = i + 1;
//                w = 0;
//            }else{
//                w += (int) (Math.ceil(widths[0]));
//                if (w > m_iTextWidth){
//                    m_iRealLine++;
//                    m_String.addElement(string.substring(istart, i));
//                    istart = i;
//                    i--;
//                    w = 0;
//                }else{
//                    if (i == (string.length() - 1)){
//                        m_iRealLine++;
//                        m_String.addElement(string.substring(istart, string.length()));
//                    }
//                }
//            }
//        }
//        m_iTextHeight=m_iRealLine*m_iFontHeight+2;
//        canvas.setViewport(m_iTextWidth, m_iTextWidth);
//        for (int i = 0, j = 0; i < m_iRealLine; i++, j++)
//        {
//            canvas.drawText((String)(m_String.elementAt(i)), x,  y+m_iFontHeight * j, mPaint);
//        }
//    }
//
//
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
//    {
//        int measuredHeight = measureHeight(heightMeasureSpec);
//        int measuredWidth = measureWidth(widthMeasureSpec);
//        this.setMeasuredDimension(measuredWidth, measuredHeight);
//        this.setLayoutParams(new LinearLayout.LayoutParams(measuredWidth,measuredHeight));
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//    }
//
//    private int measureHeight(int measureSpec)
//    {
//        int specMode = MeasureSpec.getMode(measureSpec);
//        int specSize = MeasureSpec.getSize(measureSpec);
//        // Default size if no limits are specified.
//        initHeight();
//        int result = m_iTextHeight;
//        if (specMode == MeasureSpec.AT_MOST){
//            // Calculate the ideal size of your
//            // control within this maximum size.
//            // If your control fills the available
//            // space return the outer bound.
//            result = specSize;
//        }else if (specMode == MeasureSpec.EXACTLY){
//            // If your control can fit within these bounds return that value.
//            result = specSize;
//        }
//        return result;
//    }
//
//    private void initHeight()
//    {
//        //设置 CY TextView的初始高度为0
//        m_iTextHeight=0;
//
//        //大概计算 CY TextView所需高度
//        FontMetrics fm = mPaint.getFontMetrics();
//        int m_iFontHeight = (int) Math.ceil(fm.descent - fm.top) + (int)LineSpace;
//        int line=0;
//        int istart=0;
//
//        int w=0;
//        for (int i = 0; i < string.length(); i++)
//        {
//            char ch = string.charAt(i);
//            float[] widths = new float[1];
//            String srt = String.valueOf(ch);
//            mPaint.getTextWidths(srt, widths);
//
//            if (ch == '/n'){
//                line++;
//                istart = i + 1;
//                w = 0;
//            }else{
//                w += (int) (Math.ceil(widths[0]));
//                if (w > m_iTextWidth){
//                    line++;
//                    istart = i;
//                    i--;
//                    w = 0;
//                }else{
//                    if (i == (string.length() - 1)){
//                        line++;
//                    }
//                }
//            }
//        }
//        m_iTextHeight=(line)*m_iFontHeight+2;
//    }
//
//    private int measureWidth(int measureSpec)
//    {
//        int specMode = MeasureSpec.getMode(measureSpec);
//        int specSize = MeasureSpec.getSize(measureSpec);
//
//        // Default size if no limits are specified.
//        int result = 500;
//        if (specMode == MeasureSpec.AT_MOST){
//            // Calculate the ideal size of your control
//            // within this maximum size.
//            // If your control fills the available space
//            // return the outer bound.
//            result = specSize;
//        }else if (specMode == MeasureSpec.EXACTLY){
//            // If your control can fit within these bounds return that value.
//            result = specSize;
//        }
//        return result;
//    }
//    public void SetText(String text)(//注：此函数目前只有在UI线程中调用才可以把文本画出来，在其它线程中<p>                                                        //无法画文本，找了好久找不到原因，求高手解答)
//            {
//                string = text;
//                // requestLayout();
//                // invalidate();
//            }
//}</p>
//
//
