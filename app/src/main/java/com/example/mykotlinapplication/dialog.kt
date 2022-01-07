class dialog(context: Context) {
  //android studio alert dialog kotlin
  val customDialog = AlertDialog.Builder(this)
              .setCancelable(false)
              .create()

  val dialog = dialog(this).apply {
    set(
      title = getString("back permission"),
      message = getString("go back lost the comand"),
      negativeButtonText = getString("cancel"),
      negativeButtonListener = {
        Toast.makeText(this@MainActivity, "Negative button", Toast.LENGTH_SHORT).show()
        customDialog.dismiss()
      },
      positiveButtonText = getString("accept),
      positiveButtonListener = {
        Toast.makeText(this@MainActivity, "Positive button", Toast.LENGTH_SHORT).show()
        customDialog.dismiss()
      }
    )
  }
  fun show() {
    customDialog.setView(dialog)
    customDialog.show()
  }
}
              
