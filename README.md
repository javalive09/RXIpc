# RxIpc

a lib for IPC bewteen apps(have same signature)
------

## Feature
use IPC with one line code

## Import Library
```
implementation 'com.javalive09.rxipc:rxipc:1.0.4'
```

## Usage
### 1. add permission

```
<uses-permission android:name="packagename.permission.IPC"/>
```
for example:
```
<uses-permission android:name="com.javalive09.ipc.permission.IPC"/>
```
### 2. call method
```
IPCHelper.call(cxt, getPackageName(), "method", null, null)
```
### 3. response method
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