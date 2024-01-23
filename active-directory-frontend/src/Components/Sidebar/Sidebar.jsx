import './Sidebar.css'
import React from 'react'
import {Link} from "react-router-dom";

export default function Sidebar({showSidebar}) {
  return (
      <div id={"sidebar"} className={showSidebar? "show-sidebar": "hide-sidebar"}>
        <Link to={"/users"} className={"side-item"}>Users</Link>
        <Link to={"/groups"} className={"side-item"}>Groups</Link>
      </div>
  )
}

