import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: MyHomePage(title: 'Flutter Demo Home Page'),
    );
  }
}

class MyHomePage extends StatefulWidget {
  MyHomePage({Key key, this.title, this.preference}) : super(key: key);

  final String title;
  String preference = null;

  @override
  _MyHomePageState createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  static const platform = const MethodChannel('app.channel.shared.data');

  @override
  void initState() {
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(widget.title),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            Text(widget.preference != null ? widget.preference : ""),
            ElevatedButton(
                onPressed: () async {
                  final key = "abcdefghijk";
                  platform.invokeMethod(
                      "saveToken", <String, dynamic>{'token': key});
                  setState(() {
                    widget.preference = key;
                  });
                },
                child: Text("Set Preference"))
          ],
        ),
      ),
    );
  }
}
