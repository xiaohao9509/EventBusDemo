# EventBusDemo
##这是我的关于EventBus的一个简易Demo
###1.导入EventBus
最新的EventBus:[https://github.com/greenrobot/EventBus](https://github.com/greenrobot/EventBus)

当前最新的是这个:
compile 'org.greenrobot:eventbus:3.0.0'

在build.gradle中的dependencies中添加
###2.使用EventBus
EventBus订阅者Subscribe需要注册EventBus.getDefault().register

并且还需要解除注册EventBus.getDefault().unregister

发布者Publisher只需要通过EventBus.getDefault().post发布消息内容即可

需要注意的是Android Studio中订阅者的对消息处理的方法需要加注解@Subscribe,如下:

@Subscribe

public void onEventMainThread

###3.在使用消息处理的方法的需要在接收消息的类里重写onEventXXX前缀的方法
EventBus接收消息的可重写方法有下列四种:
* 1.onEvent:
如果使用onEvent作为订阅函数，那么该事件在哪个线程发布出来的，onEvent就会在这个线程中运行，
也就是说发布事件和接收事件线程在同一个线程。使用这个方法时，在onEvent方法中不能执行耗时操作，
如果执行耗时操作容易导致事件分发延迟。
* 2.onEventMainThread:如果使用onEventMainThread作为订阅函数，那么不论事件是在哪个线程中发布出来的，
onEventMainThread都会在UI线程中执行，接收事件就会在UI线程中运行，这个在Android中是非常有用的，
因为在Android中只能在UI线程中跟新UI，所以在onEvnetMainThread方法中是不能执行耗时操作的。
* 3.onEventBackground:如果使用onEventBackgrond作为订阅函数，那么如果事件是在UI线程中发布出来的，
那么onEventBackground就会在子线程中运行，如果事件本来就是子线程中发布出来的，
那么onEventBackground函数直接在该子线程中执行。
* 4.onEventAsync：
使用这个函数作为订阅函数，那么无论事件在哪个线程发布，都会创建新的子线程再执行onEventAsync.

###4.自定义消息类
自己定义一个消息类,里面包含一个用来存储消息的属性
代码如下:
```javascript
  public class Event {
      private String msg;
      public FirstEvent(String msg) {
          this.msg = msg;
      }
      public String getMsg() {
         return msg;
     }
  }
```
###5.发布消息
Publisher发布者发送消息代码如下:
```javascript
  EventBus.getDefault().post(new Event("现在已经被点击啦!~~"));
```
只需要传一个Event并在初始化的时候传入需要发送的信息即可

###6.接收消息
Subscribe订阅者接收消息,在接收消息的界面重写onEventMainThread方法,并在其中写入需要更新的功能,
当发布者发送消息时,就会触发该方法
```javascript
    @Subscribe
    public void onEventMainThread(Event event) {
        String msg = "收到来自第二个页面的消息:" + event.getMsg();
        Log.d("harvic", msg);
        mFirstText.setText(msg);
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }
```

##总结
这样,一个简单的EventBus的简单Demo就完成了,需要注意的是,在重写接收消息方法时,必须加上注解`@Subscribe`,否则会报错,
显示在订阅者所在的类中没有public的接收消息的方法.





