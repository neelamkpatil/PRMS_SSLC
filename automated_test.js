web.init();
web.open('http://localhost:8080/phoenix/');
web.click('link=Login');
web.type('name=id', 'pointyhead');
web.type('name=password', 'pointyhead');
web.click('name=submit');
// web.pause(1000);
web.click('link=Maintain Program');
web.click('link=Create Radio Program');
web.type('name=name', 'test');
web.type('name=description', 'test');
web.type('name=typicalDuration', 'test');
// web.click('name=submit');

web.pause(10000);
 