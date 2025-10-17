# Build Instructions / تعليمات البناء

## English

### Prerequisites

1. **Java Development Kit (JDK)**
   - JDK 8 or higher
   - Download from: https://adoptium.net/

2. **Android SDK**
   - Android SDK 30 or higher
   - Can be installed via Android Studio

3. **Android Studio** (Recommended)
   - Download from: https://developer.android.com/studio
   - Version: Arctic Fox (2020.3.1) or newer

### Building with Android Studio

1. **Clone the repository:**
   ```bash
   git clone https://github.com/FaresAliGamal/Math-Tapper-By-Copilot.git
   cd Math-Tapper-By-Copilot
   ```

2. **Open in Android Studio:**
   - Launch Android Studio
   - Select "Open an Existing Project"
   - Navigate to the cloned repository folder
   - Click "OK"

3. **Sync Gradle:**
   - Android Studio will automatically start syncing
   - If not, click "File" → "Sync Project with Gradle Files"
   - Wait for the sync to complete

4. **Build the APK:**
   - Click "Build" → "Build Bundle(s) / APK(s)" → "Build APK(s)"
   - Wait for the build to complete
   - The APK will be in: `app/build/outputs/apk/debug/app-debug.apk`

5. **Install on Device:**
   - Connect your Android device via USB
   - Enable USB debugging on your device
   - Click "Run" → "Run 'app'" or press Shift+F10
   - Select your device from the list

### Building with Gradle Command Line

1. **Install Gradle** (if not using wrapper)
   ```bash
   # On Linux/Mac
   sdk install gradle 8.0
   
   # Or download from: https://gradle.org/install/
   ```

2. **Build Debug APK:**
   ```bash
   ./gradlew assembleDebug
   ```
   
   Output: `app/build/outputs/apk/debug/app-debug.apk`

3. **Build Release APK:**
   ```bash
   ./gradlew assembleRelease
   ```
   
   Output: `app/build/outputs/apk/release/app-release-unsigned.apk`

4. **Install on Connected Device:**
   ```bash
   ./gradlew installDebug
   ```

### Signing the Release APK

To distribute the app, you need to sign the release APK:

1. **Generate a keystore:**
   ```bash
   keytool -genkey -v -keystore my-release-key.jks -keyalg RSA -keysize 2048 -validity 10000 -alias my-key-alias
   ```

2. **Sign the APK:**
   ```bash
   jarsigner -verbose -sigalg SHA1withRSA -digestalg SHA1 -keystore my-release-key.jks app/build/outputs/apk/release/app-release-unsigned.apk my-key-alias
   ```

3. **Align the APK:**
   ```bash
   zipalign -v 4 app/build/outputs/apk/release/app-release-unsigned.apk app-release.apk
   ```

### Troubleshooting

**Problem: Gradle sync fails**
- Check your internet connection
- Try: File → Invalidate Caches / Restart
- Delete `.gradle` folder and sync again

**Problem: Build fails with missing SDK**
- Open SDK Manager in Android Studio
- Install Android SDK 30 or higher
- Install Android SDK Build-Tools 30.0.0 or higher

**Problem: Out of memory during build**
- Add to `gradle.properties`:
  ```
  org.gradle.jvmargs=-Xmx2048m -XX:MaxPermSize=512m
  ```

---

## العربية

<div dir="rtl">

### المتطلبات الأساسية

1. **Java Development Kit (JDK)**
   - JDK 8 أو أحدث
   - التحميل من: https://adoptium.net/

2. **Android SDK**
   - Android SDK 30 أو أحدث
   - يمكن تثبيته عبر Android Studio

3. **Android Studio** (موصى به)
   - التحميل من: https://developer.android.com/studio
   - الإصدار: Arctic Fox (2020.3.1) أو أحدث

### البناء باستخدام Android Studio

1. **استنساخ المستودع:**
   ```bash
   git clone https://github.com/FaresAliGamal/Math-Tapper-By-Copilot.git
   cd Math-Tapper-By-Copilot
   ```

2. **الفتح في Android Studio:**
   - شغّل Android Studio
   - اختر "Open an Existing Project"
   - انتقل إلى مجلد المستودع المستنسخ
   - اضغط على "OK"

3. **مزامنة Gradle:**
   - سيبدأ Android Studio تلقائياً بالمزامنة
   - إذا لم يحدث ذلك، اضغط على "File" → "Sync Project with Gradle Files"
   - انتظر حتى تكتمل المزامنة

4. **بناء APK:**
   - اضغط على "Build" → "Build Bundle(s) / APK(s)" → "Build APK(s)"
   - انتظر حتى يكتمل البناء
   - سيكون APK في: `app/build/outputs/apk/debug/app-debug.apk`

5. **التثبيت على الجهاز:**
   - وصّل جهاز الأندرويد الخاص بك عبر USB
   - فعّل تصحيح USB على جهازك
   - اضغط على "Run" → "Run 'app'" أو اضغط Shift+F10
   - اختر جهازك من القائمة

### البناء باستخدام سطر أوامر Gradle

1. **تثبيت Gradle** (إذا لم تكن تستخدم wrapper)
   ```bash
   # على Linux/Mac
   sdk install gradle 8.0
   
   # أو التحميل من: https://gradle.org/install/
   ```

2. **بناء Debug APK:**
   ```bash
   ./gradlew assembleDebug
   ```
   
   الناتج: `app/build/outputs/apk/debug/app-debug.apk`

3. **بناء Release APK:**
   ```bash
   ./gradlew assembleRelease
   ```
   
   الناتج: `app/build/outputs/apk/release/app-release-unsigned.apk`

4. **التثبيت على جهاز متصل:**
   ```bash
   ./gradlew installDebug
   ```

### توقيع Release APK

لتوزيع التطبيق، تحتاج إلى توقيع release APK:

1. **إنشاء keystore:**
   ```bash
   keytool -genkey -v -keystore my-release-key.jks -keyalg RSA -keysize 2048 -validity 10000 -alias my-key-alias
   ```

2. **توقيع APK:**
   ```bash
   jarsigner -verbose -sigalg SHA1withRSA -digestalg SHA1 -keystore my-release-key.jks app/build/outputs/apk/release/app-release-unsigned.apk my-key-alias
   ```

3. **محاذاة APK:**
   ```bash
   zipalign -v 4 app/build/outputs/apk/release/app-release-unsigned.apk app-release.apk
   ```

### حل المشاكل

**المشكلة: فشل مزامنة Gradle**
- تحقق من اتصالك بالإنترنت
- جرب: File → Invalidate Caches / Restart
- احذف مجلد `.gradle` وأعد المزامنة

**المشكلة: فشل البناء بسبب SDK مفقود**
- افتح SDK Manager في Android Studio
- قم بتثبيت Android SDK 30 أو أحدث
- قم بتثبيت Android SDK Build-Tools 30.0.0 أو أحدث

**المشكلة: نفاد الذاكرة أثناء البناء**
- أضف إلى `gradle.properties`:
  ```
  org.gradle.jvmargs=-Xmx2048m -XX:MaxPermSize=512m
  ```

</div>
