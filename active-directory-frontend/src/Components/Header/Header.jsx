import "./Header.css"
import React from 'react'
import {Hamburger} from "../Icons/Icons";
import SearchBox from "../SearchBox/SearchBox";
import {Link} from "react-router-dom";

export default function Header({showSidebar, setShowSidebar}) {
  return (
      <div id={"header"}>
        <div className={"left"}>
          <div className={"hamburger cursor-pointer"} onClick={() => setShowSidebar(!showSidebar)}><Hamburger/></div>
          <Link id={"brand"} className={"cursor-pointer"} to={"/"} style={{color:"black"}}>Active Directory</Link>
        </div>
        <div className={"center"}>
          <SearchBox/>
        </div>
        <div className={"right"}>
        </div>
      </div>
  );
}
