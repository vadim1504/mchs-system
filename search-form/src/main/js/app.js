'use strict';

const React = require('react');
const ReactDOM = require('react-dom');
const axios = require('axios');

class App extends React.Component {

	constructor(props) {
		super(props);
		this.state = {
            value: 'first_name',
            text: '',
            x1: '',
            x2: '',
            y1: '',
            y2: '',
            users: []
        }
	}

    handleChange(event) {
        this.setState({value: event.target.value});
    }

    handleText(event) {
        this.setState({text: event.target.value});
    }

    x1(event) {
        this.setState({x1: event.target.value});
    }

    x2(event) {
        this.setState({x2: event.target.value});
    }

    y1(event) {
        this.setState({y1: event.target.value});
    }

    y2(event) {
        this.setState({y2: event.target.value});
    }

    handleSearch(){
        if(this.state.value==='coordinate') {
            axios.get('http://localhost:8080/user?x1='+this.state.x1+"&x2="+this.state.x2+"&y1="+this.state.y1+"&y2="+this.state.y2).then(response=>this.setState({users: response.data}));
        }else {
            axios.get('http://localhost:8080/user?' + this.state.value + "=" + this.state.text).then(response => this.setState({users: response.data}));
        }
    }

	render() {
		return (
			<div className="container">
				<Search handleChange={this.handleChange.bind(this)} handleText={this.handleText.bind(this)} handleSearch={this.handleSearch.bind(this)} x1={this.x1.bind(this)} x2={this.x2.bind(this)} y1={this.y1.bind(this)} y2={this.y2.bind(this)}/>

				<UserList users={this.state.users}/>
			</div>
		)
	}
}

class Search extends React.Component {

    constructor(props) {
        super(props);
    }

    render() {
        return (
			<div className="jumbotron">
				<div className="row">
					<div className="col-lg-6">
						<div className="input-group">
							<input id="text" name="text" type="text" onChange={this.props.handleText} className="form-control" placeholder="Search user"/>
                        <span className="input-group-btn">
                            <button id="search" className="btn btn-default" onClick={this.props.handleSearch} type="submit">Search</button>
                        </span>
						</div>
						<div className="control-group">
							<label className="control-label">Параметры поиска</label>
							<div className="controls">
								<select id="select1" onChange={this.props.handleChange}>
									<option id="1" value="first_name">Имя</option>
									<option id="2" value="last_name">Фамилия</option>
									<option id="3" value="number">Номер телефона</option>
									<option id="4" value="coordinate">Координаты</option>
								</select>
							</div>
						</div>
					</div>
					<div className="col-lg-2">
						<input id="x1" onChange={this.props.x1} name="text" type="number" className="form-control" placeholder="X min"/>
							<input id="x2" onChange={this.props.x2} name="text" type="number" className="form-control" placeholder="X max"/>
								<input id="y1" onChange={this.props.y1} name="text" type="number" className="form-control" placeholder="Y min"/>
									<input id="y2" onChange={this.props.y2} name="text" type="number" className="form-control" placeholder="Y max"/>
					</div>
				</div>
			</div>
        )
    }
}

class UserList extends React.Component{
	render() {
		var users = this.props.users.map(user =>
			<User user={user}/>
		);
		if(this.props.users.length>0){
			return (
				<div className="table-responsive">
					<table className="table table-striped">
						<thead>
						<tr>
							<th>#</th>
							<th>Имя</th>
							<th>Фамилия</th>
							<th>Номер</th>
							<th>Координты</th>
						</tr>
						</thead>
						<tbody>
							{users}
						</tbody>
					</table>
				</div>
			)
		}else{
			return(
				<div/>
			);
		}
	}
}

class User extends React.Component{
	render() {
		return (
			<tr>
				<td>{this.props.user.id}</td>
				<td>{this.props.user.firstName}</td>
				<td>{this.props.user.lastName}</td>
				<td>{this.props.user.number}</td>
				<td>x={this.props.user.coordinateX} y={this.props.user.coordinateY}</td>
			</tr>
		)
	}
}

ReactDOM.render(
	<App />,
	document.getElementById('react')
);