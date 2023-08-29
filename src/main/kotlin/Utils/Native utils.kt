package Utils

import com.sun.jna.Native
import com.sun.jna.platform.win32.User32
import com.sun.jna.platform.win32.WinDef
import com.sun.jna.platform.win32.WinUser
import java.awt.Component
import java.awt.Window

fun setTransparent(w: Window) {
    val hwnd = WinDef.HWND()
    hwnd.setPointer(Native.getWindowPointer(w))
    var wl = User32.INSTANCE.GetWindowLong(hwnd, WinUser.GWL_EXSTYLE)
    wl = wl or WinUser.WS_EX_LAYERED or WinUser.WS_EX_TRANSPARENT
    User32.INSTANCE.SetWindowLong(hwnd, WinUser.GWL_EXSTYLE, wl)
}

fun setTransparent(w: Component) {
    val hwnd = WinDef.HWND(Native.getComponentPointer(w))
    var wl = User32.INSTANCE.GetWindowLong(hwnd, WinUser.GWL_EXSTYLE)
    wl = wl or WinUser.WS_EX_LAYERED or WinUser.WS_EX_TRANSPARENT
    User32.INSTANCE.SetWindowLong(hwnd, WinUser.GWL_EXSTYLE, wl)
}