import './PopUp.css'
import React from 'react'
import axios from "axios";
import Constants from "../../Util/Constants";

export default function PopUp({showPopUp, setShowPopUp, data}) {
  return (
    <div id={"pop-up-background"} className={showPopUp ? "show-pop-up" : "hide-pop-up"}>
      <div id={"popUpBoard"}>
        <div className={"cancel-sign"} onClick={() => setShowPopUp(false)}>x</div>
        {data}
      </div>
    </div>
  )
}
