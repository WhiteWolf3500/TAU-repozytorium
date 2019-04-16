module.exports = {
  'Fail registration': function(browser) {

    browser
      .url('http://localhost:8080/register')
      .waitForElementVisible('body', 1000)
      .verify.title('Login')
      .click('button')
      .assert.containsText(".alert", "Some fields are not filled properly")
      .assert.containsText(".form-group:nth-child(1) > .help-block", "Username is required")
      .assert.containsText(".form-group:nth-child(2) > .help-block", "First name is required")
      .assert.containsText(".form-group:nth-child(3) > .help-block", "Last name is required")
      .assert.containsText(".help-block:nth-child(6)", "Sex is required")
      .assert.containsText(".form-group:nth-child(5) > .help-block", "E-mail is required")
      .assert.containsText(".form-group:nth-child(6) > .help-block", "Password is required")
      .saveScreenshot('./test-reports/register-fail.png')
      .setValue('input[name="username"]', "SuperCoolUsername")
      .setValue('input[name="firstname"]', "Adam")
      .setValue('input[name="lastname"]', "Jankowski")
      .click('label:nth-child(3) > .radio-inline')
      .setValue('input[name="email"]', "AdamJankowski")
      .setValue('input[name="password"]', "12345")
      .click('button')
      .assert.containsText(".alert", "Some fields are not filled properly")
      .end();
    }
}