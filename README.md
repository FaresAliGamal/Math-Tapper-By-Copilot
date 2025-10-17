# Math Tapper - Auto Math Problem Solver / حل المسائل الرياضية التلقائي

[English](#english) | [العربية](#arabic)

## English

An Android application that automatically solves multiple-choice math problems by reading screen content using OCR (Optical Character Recognition) and automatically clicking the correct answer.

## Features

- **Screen Content Reading**: Captures and reads phone screen content using Accessibility Service
- **OCR Support**: Uses Google ML Kit for text recognition, can read text even from images
- **Math Problem Solving**: Automatically solves addition (+), subtraction (-), multiplication (×), and division (÷) problems
- **Region Selection**: Allows you to specify exact locations for the question and four answer choices for faster processing
- **Auto-Click**: Automatically clicks on the correct answer within 1-3 seconds
- **Customizable Regions**: Easy-to-use interface for selecting question and answer regions

## Requirements

- Android 11.0 (API level 30) or higher (required for screenshot API)
- Accessibility Service permission
- Overlay permission (for region selection)

## Installation

1. Build the APK using Android Studio or Gradle
2. Install the app on your Android device
3. Grant required permissions when prompted

## Usage

1. **Enable Accessibility Service**:
   - Open the app
   - Tap "Enable Service"
   - Find "Math Tapper" in the accessibility settings and enable it

2. **Select Regions**:
   - Tap "Select Regions" button
   - Grant overlay permission if requested
   - Position and resize the green box for each region:
     - Question Area
     - Choice 1
     - Choice 2
     - Choice 3
     - Choice 4
   - Tap "Next Region" to move to the next region
   - Tap "Save & Exit" when done

3. **Start Solving**:
   - Open any app with multiple-choice math problems
   - Return to Math Tapper app
   - Tap "Start Solving" button
   - The app will automatically read questions and click correct answers

4. **Stop Solving**:
   - Return to Math Tapper app
   - Tap "Stop Solving" button

## How It Works

1. The app uses Accessibility Service to capture screen content
2. OCR (Google ML Kit) extracts text from the specified regions
3. The math expression parser identifies and solves the problem
4. The solver compares the result with all four choices
5. Once a match is found, the app automatically clicks on that choice

## Supported Operations

- Addition: `2 + 3`, `10 + 5`
- Subtraction: `5 - 2`, `10 - 3`
- Multiplication: `4 × 3`, `5 * 6` (supports both × and *)
- Division: `8 ÷ 2`, `10 / 5` (supports both ÷ and /)

## Permissions

- **Accessibility Service**: Required to capture screen content and perform clicks
- **System Alert Window**: Required to display region selection overlay

## Technical Stack

- **Language**: Java
- **Build System**: Gradle
- **OCR**: Google ML Kit Text Recognition
- **Min SDK**: 30 (Android 11.0)
- **Target SDK**: 34 (Android 14)

## Project Structure

```
app/
├── src/main/
│   ├── java/com/mathtapper/app/
│   │   ├── MainActivity.java                      # Main activity with UI controls
│   │   ├── MathTapperAccessibilityService.java   # Accessibility service for screen capture
│   │   ├── OCRProcessor.java                      # Text recognition using ML Kit
│   │   ├── MathSolver.java                        # Math expression parser and solver
│   │   └── OverlayService.java                    # Overlay UI for region selection
│   ├── res/
│   │   ├── layout/
│   │   │   └── activity_main.xml                  # Main activity layout
│   │   ├── values/
│   │   │   └── strings.xml                        # String resources
│   │   └── xml/
│   │       └── accessibility_service_config.xml   # Accessibility service configuration
│   └── AndroidManifest.xml                        # App manifest with permissions
└── build.gradle                                    # App-level build configuration
```

## Notes

- The app requires proper region configuration for accurate results
- Performance depends on screen resolution and OCR accuracy
- Works best with clear, high-contrast text
- Processing time is typically 1-3 seconds per problem

## Language Support

The app includes full support for:
- English (Default)
- Arabic (العربية)

For detailed usage instructions in both languages, see [USAGE.md](USAGE.md).

## License

This project was created with GitHub Copilot assistance.

---

## Arabic

<div dir="rtl">

# Math Tapper - حل المسائل الرياضية التلقائي

تطبيق أندرويد يقوم بحل المسائل الرياضية ذات الاختيارات المتعددة تلقائياً عن طريق قراءة محتوى الشاشة باستخدام تقنية التعرف الضوئي على الحروف (OCR) والنقر تلقائياً على الإجابة الصحيحة.

## المميزات

- **قراءة محتوى الشاشة**: يلتقط ويقرأ محتوى شاشة الهاتف باستخدام خدمة الوصول
- **دعم OCR**: يستخدم Google ML Kit للتعرف على النص، يمكنه قراءة النص حتى من الصور
- **حل المسائل الرياضية**: يحل تلقائياً مسائل الجمع (+)، الطرح (-)، الضرب (×)، والقسمة (÷)
- **اختيار المناطق**: يتيح لك تحديد المواقع الدقيقة للسؤال والاختيارات الأربعة لمعالجة أسرع
- **النقر التلقائي**: ينقر تلقائياً على الإجابة الصحيحة خلال 1-3 ثواني
- **مناطق قابلة للتخصيص**: واجهة سهلة الاستخدام لاختيار مناطق السؤال والإجابات

## المتطلبات

- أندرويد 11.0 (API level 30) أو أحدث (مطلوب لواجهة برمجة تطبيقات لقطة الشاشة)
- إذن خدمة الوصول
- إذن التراكب (لاختيار المناطق)

## التثبيت

1. قم ببناء APK باستخدام Android Studio أو Gradle
2. قم بتثبيت التطبيق على جهاز الأندرويد الخاص بك
3. امنح الأذونات المطلوبة عند الطلب

## الاستخدام

### 1. تفعيل خدمة الوصول:
- افتح التطبيق
- اضغط على "تفعيل الخدمة"
- ابحث عن "Math Tapper" في إعدادات الوصول وقم بتفعيله

### 2. اختيار المناطق:
- اضغط على زر "اختيار المناطق"
- امنح إذن التراكب إذا طُلب منك
- ضع وغيّر حجم الصندوق الأخضر لكل منطقة:
  - منطقة السؤال
  - الاختيار 1
  - الاختيار 2
  - الاختيار 3
  - الاختيار 4
- اضغط على "المنطقة التالية" للانتقال إلى المنطقة التالية
- اضغط على "حفظ والخروج" عند الانتهاء

### 3. بدء الحل:
- افتح أي تطبيق يحتوي على مسائل رياضية اختيارية
- ارجع إلى تطبيق Math Tapper
- اضغط على زر "بدء الحل"
- سيقرأ التطبيق تلقائياً الأسئلة وينقر على الإجابات الصحيحة

### 4. إيقاف الحل:
- ارجع إلى تطبيق Math Tapper
- اضغط على زر "إيقاف الحل"

## كيف يعمل

1. التطبيق يستخدم خدمة الوصول لالتقاط محتوى الشاشة
2. تقنية OCR (Google ML Kit) تستخرج النص من المناطق المحددة
3. محلل التعبيرات الرياضية يحدد ويحل المسألة
4. الحلال يقارن النتيجة مع جميع الاختيارات الأربعة
5. بمجرد العثور على تطابق، يقوم التطبيق بالنقر تلقائياً على ذلك الاختيار

## العمليات المدعومة

- الجمع: `2 + 3`, `10 + 5`
- الطرح: `5 - 2`, `10 - 3`
- الضرب: `4 × 3`, `5 * 6` (يدعم كل من × و *)
- القسمة: `8 ÷ 2`, `10 / 5` (يدعم كل من ÷ و /)

## الأذونات

- **خدمة الوصول**: مطلوبة لالتقاط محتوى الشاشة وإجراء النقرات
- **نافذة تنبيه النظام**: مطلوبة لعرض تراكب اختيار المناطق

## التقنيات المستخدمة

- **اللغة**: Java
- **نظام البناء**: Gradle
- **OCR**: Google ML Kit Text Recognition
- **الحد الأدنى SDK**: 30 (أندرويد 11.0)
- **الهدف SDK**: 34 (أندرويد 14)

## ملاحظات

- التطبيق يتطلب إعداد المناطق بشكل صحيح للحصول على نتائج دقيقة
- الأداء يعتمد على دقة الشاشة ودقة OCR
- يعمل بشكل أفضل مع النص الواضح عالي التباين
- وقت المعالجة عادة 1-3 ثواني لكل مسألة

## دعم اللغات

التطبيق يتضمن دعماً كاملاً لـ:
- الإنجليزية (افتراضي)
- العربية

للحصول على تعليمات الاستخدام التفصيلية بكلتا اللغتين، راجع [USAGE.md](USAGE.md).

## الترخيص

تم إنشاء هذا المشروع بمساعدة GitHub Copilot.

</div>