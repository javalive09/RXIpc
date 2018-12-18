# RxIpc

a lib for IPC bewteen apps(have same signature)


## Feature
use IPC with one line code

## Import Library
both apps need depends library
```
implementation 'com.javalive09.rxipc:rxipc:1.0.4'
```

## Usage
app A call app B test() need 3 step as follow:

### 1. app A need config permission in AndroidManifest.xml
```
<uses-permission android:name="appBpackagename.permission.IPC"/>
as: 
<uses-permission android:name="com.javalive09.ipc.permission.IPC"/>
```
### 2. app A call app B test()
```
IPCHelper.call(cxt, appBpackagename, "test", null, null)
as:
IPCHelper.call(cxt, "com.javalive09.ipc", "test", null, null)
```
### 3. app B response 
implements IMethod interface
```
registerMethod(IMethod method, String... orders)
unregisterMethod(IMethod method)
```

## License
```
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```