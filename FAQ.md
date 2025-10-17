# Frequently Asked Questions (FAQ) / الأسئلة الشائعة

## English

### General Questions

**Q: What is Math Tapper?**
A: Math Tapper is an Android application that automatically reads and solves multiple-choice math problems from your screen and clicks on the correct answer.

**Q: Which Android versions are supported?**
A: Android 11 (API level 30) or higher is required. This is necessary for the screenshot API.

**Q: Is my data safe?**
A: Yes! All processing is done locally on your device. No data is sent to external servers. The app only uses permissions for capturing screen content and clicking, as stated.

**Q: Does this work with any math problem app?**
A: The app works with any app that displays multiple-choice math problems in a standard format. You need to configure the regions for question and answer locations.

**Q: How fast does it solve problems?**
A: Typically 1-3 seconds per problem, depending on your device's performance and the clarity of the text.

### Setup and Configuration

**Q: Why do I need Accessibility Service permission?**
A: Accessibility Service is required to:
- Capture screenshots of your screen
- Perform automatic clicks on the correct answer
This is the only way Android allows apps to perform these functions.

**Q: Why do I need Overlay permission?**
A: Overlay permission is needed to display the region selection interface on top of other apps, allowing you to select the question and answer areas.

**Q: How do I properly set up regions?**
A: 
1. Open the app with math problems you want to solve
2. Note the positions of the question and four answer choices
3. Open Math Tapper and tap "Select Regions"
4. Position the green box over each region one by one
5. Make sure the box fully contains the text
6. Save when done

**Q: Can I adjust regions after saving?**
A: Yes! Just tap "Select Regions" again and reposition the boxes. Your new settings will replace the old ones.

### Troubleshooting

**Q: Why is the app clicking on wrong answers?**
A: This usually happens due to:
- Incorrectly positioned regions
- Poor text contrast or quality
- Unsupported math expression format
- OCR misreading the text

Solutions:
- Reconfigure your regions to ensure they're accurate
- Use apps with clear, high-contrast text
- Ensure your expressions use standard operators (+, -, ×, ÷)

**Q: Why doesn't anything happen when I enable solving?**
A: Check these:
- Is Accessibility Service enabled? (Check in Settings)
- Are regions properly configured? (Check in app)
- Is "Start Solving" activated? (Button should show "Stop Solving")
- Are you viewing a screen with math problems?

**Q: The app says "Screenshot failed"**
A: This can happen if:
- Your Android version is below 11
- Accessibility Service got disabled
- Another app is blocking screenshot capability
- System is in secure mode (e.g., banking apps)

**Q: Why is OCR not recognizing text?**
A: Improve OCR accuracy by:
- Using apps with larger, clearer text
- Ensuring good contrast (dark text on light background works best)
- Avoiding handwritten or stylized fonts
- Making sure regions are properly aligned

**Q: Can I use this with my language/locale?**
A: The app supports multiple languages for the interface (English and Arabic currently). OCR works with Latin characters and numbers, which are universal for math expressions.

### Features and Limitations

**Q: What math operations are supported?**
A: Currently supported:
- Addition (+)
- Subtraction (-)
- Multiplication (× or *)
- Division (÷ or /)

Simple expressions only (e.g., "5 + 3", not "5 + 3 - 2").

**Q: Can it solve complex equations?**
A: No, currently only simple two-operand arithmetic is supported. Complex equations, parentheses, and multi-step problems are not supported.

**Q: Does it work with word problems?**
A: No, it only processes mathematical expressions with numbers and operators. It doesn't understand word problems.

**Q: Can I use this in exams?**
A: This tool is for educational and practice purposes only. Using automated tools in official exams may violate academic integrity policies.

**Q: Will this work on all screen sizes?**
A: Yes, the region selection is flexible and can be adjusted for any screen size or orientation.

### Privacy and Security

**Q: What permissions does the app use?**
A: 
- **Accessibility Service**: To capture screen and perform clicks
- **Overlay Permission**: To show region selection interface

**Q: Is my screen content recorded or uploaded?**
A: No! Screenshots are processed locally and immediately discarded. Nothing is saved or uploaded.

**Q: Can this app access my other apps' data?**
A: No. The app only captures visual content from the screen. It cannot access app data, files, or databases.

### Development and Contribution

**Q: Is this open source?**
A: Yes! The code is available on GitHub. You can contribute, report issues, or fork the project.

**Q: How can I contribute?**
A: See [CONTRIBUTING.md](CONTRIBUTING.md) for guidelines on how to contribute to the project.

**Q: Can I request new features?**
A: Yes! Open an issue on GitHub with your feature request. We welcome ideas from the community.

**Q: I found a bug, what should I do?**
A: Please open an issue on GitHub with:
- Device model and Android version
- Steps to reproduce the bug
- Expected vs actual behavior
- Screenshots if possible

---

## العربية

<div dir="rtl">

### أسئلة عامة

**س: ما هو Math Tapper؟**
ج: Math Tapper هو تطبيق أندرويد يقرأ ويحل تلقائياً المسائل الرياضية ذات الاختيارات المتعددة من شاشتك وينقر على الإجابة الصحيحة.

**س: ما هي إصدارات أندرويد المدعومة؟**
ج: يُطلب أندرويد 11 (API level 30) أو أحدث. هذا ضروري لواجهة برمجة تطبيقات لقطة الشاشة.

**س: هل بياناتي آمنة؟**
ج: نعم! تتم جميع المعالجات محلياً على جهازك. لا يتم إرسال أي بيانات إلى خوادم خارجية. يستخدم التطبيق الأذونات فقط لالتقاط محتوى الشاشة والنقر، كما هو مذكور.

**س: هل يعمل هذا مع أي تطبيق مسائل رياضية؟**
ج: يعمل التطبيق مع أي تطبيق يعرض مسائل رياضية اختيارية بتنسيق قياسي. تحتاج إلى تكوين المناطق لمواقع السؤال والإجابات.

**س: ما مدى سرعة حل المسائل؟**
ج: عادة 1-3 ثواني لكل مسألة، حسب أداء جهازك ووضوح النص.

### الإعداد والتكوين

**س: لماذا أحتاج إلى إذن خدمة الوصول؟**
ج: خدمة الوصول مطلوبة لـ:
- التقاط لقطات شاشة لشاشتك
- إجراء نقرات تلقائية على الإجابة الصحيحة
هذه هي الطريقة الوحيدة التي يسمح بها أندرويد للتطبيقات بأداء هذه الوظائف.

**س: لماذا أحتاج إلى إذن التراكب؟**
ج: إذن التراكب مطلوب لعرض واجهة اختيار المناطق فوق التطبيقات الأخرى، مما يسمح لك باختيار مناطق السؤال والإجابات.

**س: كيف أقوم بإعداد المناطق بشكل صحيح؟**
ج:
1. افتح التطبيق الذي يحتوي على مسائل رياضية تريد حلها
2. لاحظ مواضع السؤال والاختيارات الأربعة
3. افتح Math Tapper واضغط على "اختيار المناطق"
4. ضع الصندوق الأخضر فوق كل منطقة واحدة تلو الأخرى
5. تأكد من أن الصندوق يحتوي على النص بالكامل
6. احفظ عند الانتهاء

**س: هل يمكنني تعديل المناطق بعد الحفظ؟**
ج: نعم! فقط اضغط على "اختيار المناطق" مرة أخرى وأعد وضع الصناديق. ستحل إعداداتك الجديدة محل القديمة.

### حل المشاكل

**س: لماذا ينقر التطبيق على إجابات خاطئة؟**
ج: يحدث هذا عادة بسبب:
- مناطق موضوعة بشكل غير صحيح
- سوء تباين أو جودة النص
- تنسيق تعبير رياضي غير مدعوم
- OCR يقرأ النص بشكل خاطئ

الحلول:
- أعد تكوين مناطقك للتأكد من دقتها
- استخدم تطبيقات ذات نص واضح عالي التباين
- تأكد من أن تعابيرك تستخدم عوامل التشغيل القياسية (+، -، ×، ÷)

**س: لماذا لا يحدث شيء عندما أفعّل الحل؟**
ج: تحقق من هذه:
- هل خدمة الوصول مفعلة؟ (تحقق من الإعدادات)
- هل المناطق مكونة بشكل صحيح؟ (تحقق في التطبيق)
- هل "بدء الحل" مفعل؟ (يجب أن يظهر الزر "إيقاف الحل")
- هل تشاهد شاشة تحتوي على مسائل رياضية؟

**س: يقول التطبيق "فشل التقاط الشاشة"**
ج: يمكن أن يحدث هذا إذا:
- إصدار أندرويد الخاص بك أقل من 11
- تم تعطيل خدمة الوصول
- تطبيق آخر يحظر إمكانية التقاط الشاشة
- النظام في وضع آمن (مثل تطبيقات البنوك)

**س: لماذا لا تتعرف OCR على النص؟**
ج: حسّن دقة OCR عن طريق:
- استخدام تطبيقات ذات نص أكبر وأوضح
- ضمان تباين جيد (النص الداكن على خلفية فاتحة يعمل بشكل أفضل)
- تجنب الخطوط المكتوبة بخط اليد أو الخطوط المميزة
- التأكد من محاذاة المناطق بشكل صحيح

**س: هل يمكنني استخدام هذا مع لغتي/إعداداتي المحلية؟**
ج: يدعم التطبيق لغات متعددة للواجهة (الإنجليزية والعربية حالياً). يعمل OCR مع الأحرف اللاتينية والأرقام، والتي هي عالمية للتعبيرات الرياضية.

### الميزات والقيود

**س: ما هي العمليات الرياضية المدعومة؟**
ج: المدعومة حالياً:
- الجمع (+)
- الطرح (-)
- الضرب (× أو *)
- القسمة (÷ أو /)

تعبيرات بسيطة فقط (مثل "5 + 3"، وليس "5 + 3 - 2").

**س: هل يمكنه حل معادلات معقدة؟**
ج: لا، حالياً يُدعم فقط الحساب البسيط بمعاملين. المعادلات المعقدة والأقواس والمسائل متعددة الخطوات غير مدعومة.

**س: هل يعمل مع المسائل اللفظية؟**
ج: لا، يعالج فقط التعبيرات الرياضية بالأرقام والعوامل. لا يفهم المسائل اللفظية.

**س: هل يمكنني استخدام هذا في الامتحانات؟**
ج: هذه الأداة للأغراض التعليمية والممارسة فقط. استخدام الأدوات الآلية في الامتحانات الرسمية قد ينتهك سياسات النزاهة الأكاديمية.

**س: هل سيعمل هذا على جميع أحجام الشاشات؟**
ج: نعم، اختيار المنطقة مرن ويمكن تعديله لأي حجم شاشة أو اتجاه.

### الخصوصية والأمان

**س: ما هي الأذونات التي يستخدمها التطبيق؟**
ج:
- **خدمة الوصول**: لالتقاط الشاشة وإجراء النقرات
- **إذن التراكب**: لإظهار واجهة اختيار المناطق

**س: هل يتم تسجيل أو تحميل محتوى شاشتي؟**
ج: لا! تتم معالجة لقطات الشاشة محلياً ويتم التخلص منها فوراً. لا يتم حفظ أو تحميل أي شيء.

**س: هل يمكن لهذا التطبيق الوصول إلى بيانات تطبيقاتي الأخرى؟**
ج: لا. يلتقط التطبيق المحتوى المرئي من الشاشة فقط. لا يمكنه الوصول إلى بيانات التطبيق أو الملفات أو قواعد البيانات.

### التطوير والمساهمة

**س: هل هذا مفتوح المصدر؟**
ج: نعم! الكود متاح على GitHub. يمكنك المساهمة أو الإبلاغ عن المشاكل أو عمل fork للمشروع.

**س: كيف يمكنني المساهمة؟**
ج: راجع [CONTRIBUTING.md](CONTRIBUTING.md) للحصول على إرشادات حول كيفية المساهمة في المشروع.

**س: هل يمكنني طلب ميزات جديدة؟**
ج: نعم! افتح issue على GitHub مع طلب الميزة الخاص بك. نرحب بالأفكار من المجتمع.

**س: وجدت خطأ، ماذا يجب أن أفعل؟**
ج: يرجى فتح issue على GitHub مع:
- طراز الجهاز وإصدار أندرويد
- خطوات إعادة إنتاج الخطأ
- السلوك المتوقع مقابل السلوك الفعلي
- لقطات شاشة إن أمكن

</div>
