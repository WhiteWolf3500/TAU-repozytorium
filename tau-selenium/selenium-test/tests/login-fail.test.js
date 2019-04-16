module.exports = {
  'Fail login': function(browser) {

    browser
      .url('http://localhost:8080')
      .waitForElementVisible('body', 1000)
      .verify.title('Login')
      .setValue('input[name="username"]', "asdf")
      .setValue('input[name="password"]', "asdf")
      .click('button')
      .waitForElementVisible(".alert-danger", 1000)
      .assert.containsText(".alert-danger", "Username or password is incorrect")
      .saveScreenshot('./test-reports/login-fail.png')
      .end();
    }
}