# RichText

#### 解决问题
使用链式结构组成SpannableStringBuilder。使得结构上更加清晰，使用时更加方便。

#### 使用对比

代码对比：
```java
//链式结构
    private fun initContentTop() {
        RichText.Builder(this)
                .backColor(1, 4, Color.RED)
                .foreColor(5, 9, Color.BLUE)
                .fontSize(10, 15, 20)
                .image(2, 3, resources.getDrawable(R.mipmap.ic_launcher))
                .addTextSpanOperation(TextSpanOperation(3, 4, AbsoluteSizeSpan(20)))
                .text(TEXT)
                .build(tvContentTop)
    }

    //常规写法
    private fun initContentBottom() {
        var backColor = BackgroundColorSpan(Color.RED)
        var foreColor = ForegroundColorSpan(Color.BLUE)
        var fontSize = AbsoluteSizeSpan(20)
        var d = resources.getDrawable(R.mipmap.ic_launcher)
        d.setBounds(0, 0, d.intrinsicWidth, d.intrinsicHeight)
        var image = ImageSpan(d)
        var fontSize2 = AbsoluteSizeSpan(20)
        var spanBuilder = SpannableStringBuilder(TEXT)
        spanBuilder.setSpan(backColor, 1, 4, SpannableStringBuilder.SPAN_EXCLUSIVE_EXCLUSIVE)
        spanBuilder.setSpan(foreColor, 5, 9, SpannableStringBuilder.SPAN_EXCLUSIVE_EXCLUSIVE)
        spanBuilder.setSpan(fontSize, 10, 15, SpannableStringBuilder.SPAN_EXCLUSIVE_EXCLUSIVE)
        spanBuilder.setSpan(image, 2, 3, SpannableStringBuilder.SPAN_EXCLUSIVE_EXCLUSIVE)
        spanBuilder.setSpan(fontSize2, 3, 4, SpannableStringBuilder.SPAN_EXCLUSIVE_EXCLUSIVE)
        tvContentBottom.text = spanBuilder
    }
```

相对来说还是上面的链式结构的代码会比较明了。


#### 如何使用
Add it in your root build.gradle at the end of repositories:
```gradle
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
Step 2. Add the dependency
```gradle
	dependencies {
	        implementation 'com.github.javalong:RichText:1.0.0'
	}
```

Step 3. Multiple text span operation
```java
      //设置文本
      fun text(text: String): Builder {
            this.text = text
            return this
        }

        //设置图片icon
        fun image(startIndex: Int, endIndex: Int, d: Drawable): Builder {
            d.setBounds(0, 0, d.intrinsicWidth, d.intrinsicHeight)
            var span = ImageSpan(d)
            operationList.add(TextSpanOperation(startIndex, endIndex, span))
            return this
        }
     //设置字体大小
        fun fontSize(startIndex: Int, endIndex: Int, size: Int): Builder {
            var span = AbsoluteSizeSpan(size)
            operationList.add(TextSpanOperation(startIndex, endIndex, span))
            return this
        }

        //设置字体颜色
        fun foreColor(startIndex: Int, endIndex: Int, color: Int): Builder {
            var span = ForegroundColorSpan(color)
            operationList.add(TextSpanOperation(startIndex, endIndex, span))
            return this
        }

        //设置背景颜色
        fun backColor(startIndex: Int, endIndex: Int, color: Int): Builder {
            var span = BackgroundColorSpan(color)
            operationList.add(TextSpanOperation(startIndex, endIndex, span))
            return this
        }
```

Step 4. Use in code
```java
 RichText.Builder(this)             
                .backColor(1, 4, Color.RED)
                .foreColor(5, 9, Color.BLUE)
                .fontSize(10, 15, 20)
                .image(2, 3, resources.getDrawable(R.mipmap.ic_launcher))
                .addTextSpanOperation(TextSpanOperation(3, 4, AbsoluteSizeSpan(20)))
                .text(TEXT)
                .build(tvContentTop)
```
重点可以关注下`addTextSpanOperation`方法，因为TextSpan有很多，这里就把常见的几个抽取出来。

Step 5. Adapt EditText
EditText也是TextView，所以这里也适用。
```java
RichText.Builder(this)
                .backColor(1, 4, Color.RED,SpannableStringBuilder.SPAN_EXCLUSIVE_EXCLUSIVE)
                .foreColor(5, 9, Color.BLUE,SpannableStringBuilder.SPAN_EXCLUSIVE_EXCLUSIVE)
                .fontSize(10, 15, 20,SpannableStringBuilder.SPAN_EXCLUSIVE_EXCLUSIVE)
                .image(2, 3, resources.getDrawable(R.mipmap.ic_launcher))
                .addTextSpanOperation(TextSpanOperation(3, 4, AbsoluteSizeSpan(20)))
                .text(TEXT)
                .build(etContentTop)
```
操作符方法中最后一个参数是`SpannableStringBuilder.SPAN_EXCLUSIVE_EXCLUSIVE`。在`EditText`会用的比较多。可参考链接。
https://blog.csdn.net/lanxingfeifei/article/details/50523555
