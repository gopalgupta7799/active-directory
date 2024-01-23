import './App.css';
import React, {useState} from "react";
import {BrowserRouter, Outlet, Route, Routes} from "react-router-dom";
import Header from "./Components/Header/Header";
import Users from "./Components/Users/Users";
import Groups from "./Components/Groups/Groups";
import Home from "./Components/Home/Home";
import Sidebar from "./Components/Sidebar/Sidebar";
import User from "./Components/User/User";
import Group from "./Components/Group/Group";

function App() {
  const [showSidebar, setShowSidebar] = useState(false);

  return (
    <BrowserRouter>
      <div className="App">
        <Header showSidebar={showSidebar} setShowSidebar={setShowSidebar}/>
        <div id={"bottom"}>
          <Sidebar showSidebar={showSidebar}/>
          <main id={"content"}>
            <Routes>
              <Route path={""} element={<Home/>}/>

              <Route path={"users"} element={<Outlet/>}>
                <Route index element={<Users/>}/>
                <Route path={":userId"} element={<Outlet/>}>
                  <Route index element={<User/>}/>
                  <Route path={"groups"} element={<Groups/>}/>
                </Route>
              </Route>

              <Route path="groups" element={<Outlet/>}>
                <Route index element={<Groups/>}/>
                <Route path={":groupId"} element={<Group/>}/>
                <Route path={":groupId/users"} element={<Users/>}/>
              </Route>

            </Routes>
          </main>
        </div>
      </div>
    </BrowserRouter>
  );
}

export default App;
